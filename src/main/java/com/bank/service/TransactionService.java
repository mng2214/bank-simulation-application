package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Transaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction makeTransfer (Account sender, Account receiver, BigDecimal amount, Date creationDate, String message);

    List<Transaction> findAllTransaction ();

    List<Transaction> last10Transactions();

    List<Transaction> findTransactionListById(UUID id);
}
