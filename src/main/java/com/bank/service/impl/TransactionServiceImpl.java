package com.bank.service.impl;

import com.bank.Entity.Account;
import com.bank.Entity.Transaction;
import com.bank.dto.AccountDTO;
import com.bank.enums.AccountType;
import com.bank.exception.*;
import com.bank.dto.TransactionDTO;
import com.bank.mapper.AccountMapper;
import com.bank.mapper.TransactionMapper;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {
    @Value("${under_construction}")
    private boolean underConstruction;
    private final AccountService accountService;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(AccountService accountService, TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }


    @Override
    public TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {
        if(!underConstruction) {
        /*
            -if sender or receiver is null ?
            -if sender and receiver is the same account ?
            -if sender has enough balance to make transfer ?
            if both accounts are checking, if not, one of them saving, it needs to be same userId
         */
            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);

         /*
            after all validations are completed, and money is transferred, we need to create Transaction object save/return it.
          */
            TransactionDTO transactionDTO = new TransactionDTO(sender,receiver,amount,message,creationDate);

            //save into db and return it
            transactionRepository.save(transactionMapper.convertToEntity(transactionDTO));
            return transactionDTO;

        }else {
            throw new UnderConstructionException("App is under construction, please try again later");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {
        if(checkSenderBalance(sender,amount)){
            //update sender and receiver
            //100 - 80  = 20
            sender.setBalance(sender.getBalance().subtract(amount));
            //50 + 80 = 130
            receiver.setBalance(receiver.getBalance().add(amount));

            /*
                get the dto from the database for both sender and receiver, update balance and save it
                create accountService updateAccount method and use it for saving
             */
            //find accounts by id
            AccountDTO senderAcc = accountService.findById(sender.getId());
            senderAcc.setBalance(sender.getBalance());
            accountService.updateAccount(senderAcc);

            AccountDTO receiverAcc = accountService.findById(receiver.getId());
            receiverAcc.setBalance(receiver.getBalance());
            accountService.updateAccount(receiverAcc);

        }else {
            throw new BalanceNotSufficientException("Balance is not enough for this transfer");
        }


    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        //verify sender has enough balance to make transfer
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >=0;
    }

    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {
        /*  TASK
            write an if statement that checks if one of the account is saving,
            and user of sender or receiver is not the same, throw AccountOwnershipException
         */
        //if(senderOrReceiverIsSaving)And(!UserOfSenderAndReceiverMustBeTheSame)
        //throw Exception
        if((sender.getAccountType().equals(AccountType.SAVING)||receiver.getAccountType().equals(AccountType.SAVING))
                && !sender.getUserId().equals(receiver.getUserId())){
            throw new AccountOwnershipException("If one of the account is saving, user must be the same for sender and receiver");
        }

    }

    private void validateAccount(AccountDTO sender, AccountDTO receiver) {
            /*
                -if any of the account is null
                -if accounts ids are the same(same account)
                -if the account is exist (repository)
             */

        if(sender==null||receiver==null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }

        //if accounts are the same throw BadRequestException with saying accounts needs to be different
        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different than receiver account");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(Long id) {
        accountService.findById(id);
    }

    @Override
    public List<TransactionDTO> findAllTransaction() {
        //we need to convert transaction entities to dtos
        return transactionRepository.findAll().stream().map(transactionMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> last10Transactions() {
        //we want to get last 10 transaction
        //write a query to get the result of last 10 transactions
        List<Transaction> last10Transaction = transactionRepository.findLast10Transaction();
        //convert it to dto and return
        return last10Transaction.stream().map(transactionMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findTransactionListById(Long id) {
        //get the list of transactions if account id is involved as a sender or receiver
        List<Transaction> transactionList = transactionRepository.findTransactionListByAccountId(id);

        //convert list of entity to dto and return it
        return transactionList.stream().map(transactionMapper::convertToDTO).collect(Collectors.toList());

    }
}
