package com.bank.repository;

import com.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transactions ORDER BY create_date DESC LIMIT 10",nativeQuery = true)
    List<Transaction> findLast10Transaction();

    @Query("SELECT t FROM Transaction t WHERE t.sender.id = :accId OR t.receiver.id = :accId")
    List<Transaction> findTransactionListByAccountId(@Param("accId")Long id);

    //List<Transaction> findAllById(Long id);

}
