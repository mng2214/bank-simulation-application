package com.bank.repository;

import com.bank.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transactions ORDER BY create_date DESC LIMIT 10",nativeQuery = true)
    List<Transaction> findLast10Transaction();

    @Query("SELECT t FROM Transaction t WHERE t.sender.id = ?1 OR t.receiver.id = ?1")
    List<Transaction> findTransactionListByAccountId(Long id);

    //List<Transaction> findTop10ByOrderByCreateDateDesc();
}
