package com.bank.repository;

import com.bank.exception.RecordNotFoundException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionRepository {
    public static List<Transaction> transactionList = new ArrayList<>();

    public Transaction save(Transaction transaction) {
        transactionList.add(transaction);
        return transaction;
    }

    public List<Transaction> findAllAccounts() {
        return transactionList;
    }

}
