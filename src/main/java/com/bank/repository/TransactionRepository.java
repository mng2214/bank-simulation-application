package com.bank.repository;

import com.bank.exception.RecordNotFoundException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionRepository {
    public static List<Transaction> transactionList = new ArrayList<>();

    public Transaction save(Transaction transaction) {
        transactionList.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll() {
        return transactionList;
    }

    public List<Transaction> getLast10Transactions() {
        return findAll().stream()
                .sorted(Comparator.comparing(Transaction::getCreateDate).reversed())
                .limit(10)
                .toList();
    }

}
