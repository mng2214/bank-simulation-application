package com.bank.repository;

import com.bank.dto.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public interface TransactionRepository extends JpaRepository<TransactionDTO, Integer> {

//    public static List<TransactionDTO> transactionDTOList = new ArrayList<>();
//
//    public TransactionDTO save(TransactionDTO transactionDTO) {
//        transactionDTOList.add(transactionDTO);
//        return transactionDTO;
//    }
//
//    public List<TransactionDTO> findAll() {
//        return transactionDTOList;
//    }
//
//    public List<TransactionDTO> getLast10Transactions() {
//        return findAll().stream()
//                .sorted(Comparator.comparing(TransactionDTO::getCreateDate).reversed())
//                .limit(10)
//                .toList();
//    }


}
