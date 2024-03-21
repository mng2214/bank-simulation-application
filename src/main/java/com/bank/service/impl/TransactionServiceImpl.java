package com.bank.service.impl;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.service.TransactionService;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        /*
        list of validations:
        if sender/receiver is null?
        if sender/receiver same account
        if sender have enough balance
        if both accounts are checking? if not is saving then need to be same user id
         */
        validateAccount(sender, receiver);

        return null;
    }

    @SneakyThrows
    private void validateAccount(Account sender, Account receiver) {
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

        findAccountById (sender.getId());
        findAccountById (receiver.getId());


        // if accounts are the same throw Exception. Accounts needs to be different
    }

    private void findAccountById(UUID id) {
        accountRepository.findById(id);
    }


    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }


}
