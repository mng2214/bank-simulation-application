package com.bank.service.impl;

import com.bank.dto.AccountDTO;
import com.bank.enums.AccountType;
import com.bank.exception.AccountBalanceException;
import com.bank.exception.AccountOwnershipException;
import com.bank.exception.UnderConstructionException;
import com.bank.dto.TransactionDTO;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.TransactionService;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = Logger.getLogger(TransactionServiceImpl.class.getName());

    @Value("${under_construction}")
    private boolean underConstruction;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepositoryl) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepositoryl;
    }

    @Override
    public TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {
        if (!underConstruction) {
    /*
        list of validations:
        if sender/receiver is null?
        if sender/receiver same account
        if sender have enough balance
        if both accounts are checking? if not is saving then need to be same user id
         */
            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);
            // after all validations are complete we need to create transaction object and save data

            TransactionDTO transactionDTO = new TransactionDTO();

            // save it to db and return it


            logger.info(String.format("Amount of $%s From : %s To : %s Has been sent.%nTransaction completed",
                    transactionDTO.getAmount(), sender.getId(), receiver.getId()));

            return transactionRepository.save(transactionDTO);
        } else {
            throw new UnderConstructionException("Account is under construction");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {
        if (checkSenderBalance(sender, amount)) {
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        } else {
            throw new AccountBalanceException("Insufficient funds. Account balance is too low");
        }
    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;
    }

//then we needs to build small back door:) like some secret api post call will increase your amount some $

    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {
        // if (senderOrReceiverIsSaving) and (!userOfSenderAndReceiverMustBeTheSame)
        if ((sender.getAccountType().equals(AccountType.SAVING)
                || receiver.getAccountType().equals(AccountType.SAVING))
                && !sender.getUserId().equals(receiver.getUserId())) {
            throw new AccountOwnershipException("If one of the account is saving, or user must mbe the same for sender and receiver ");
        }
    }

    @SneakyThrows
    private void validateAccount(AccountDTO sender, AccountDTO receiver) {
        /*
        if any of accounts is null
        if accounts isd are the same (same account)
        if the account is existed (repository)
         */
        if (sender == null || receiver == null) {
            throw new BadRequestException("Sender or Receiver can not be null");
        }
        if (sender.getId() == receiver.getId()) {
            throw new BadRequestException("Sender account needs to be different then receiver account");
        }
        findAccountById(sender.getId());
        findAccountById(receiver.getId());
    }

    private void findAccountById(Long id) {
        accountRepository.findById(id);
    }

    @Override
    public List<TransactionDTO> findAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<TransactionDTO> last10Transactions() {
        return transactionRepository.getLast10Transactions();
    }

    @Override
    public List<TransactionDTO> findTransactionListById(Long id) {
        return transactionRepository.findAll()
                .stream().filter(t -> t.getSender().equals(id) || t.getReceiver().equals(id))
                .collect(Collectors.toList());
    }

}
