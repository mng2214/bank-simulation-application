package com.bank.service.impl;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.TransactionService;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        /*
        list of validations
        if sender/receiver is null?
        if sender/receiver same account
        if sender have enough balance
        if both accounts are checking? if not is saving then need to be same user id
         */
        validateAccount(sender, receiver);

        return null;
    }

    private void validateSameAccount(BigDecimal amount) {

    }

    @SneakyThrows
    private void validateAccount(Account sender, Account receiver) {
        /*
        if any of accounts is null
        if accounts isd are the same (same accout)
        if the account is exits (repository)
         */
        if (sender == null || receiver == null) {
            throw new BadRequestException("Sender or Receiver can not be null");
        }
        if (sender.getId() == receiver.getId()) {
            throw new BadRequestException("Sender account needs to be different then receiver account");
        }

        // if accounts are the same throw Exception. Accounts needs to be different
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }


}
