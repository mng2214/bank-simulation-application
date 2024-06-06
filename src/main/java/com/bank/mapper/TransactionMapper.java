package com.bank.mapper;

import com.bank.Entity.Account;
import com.bank.Entity.Transaction;
import com.bank.dto.AccountDTO;
import com.bank.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionMapper {

    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Transaction toEntity(TransactionDTO account) {
        return modelMapper.map(account, Transaction.class);
    }

    public TransactionDTO toDto(Transaction account, Long id) {
        return modelMapper.map(account, TransactionDTO.class);
    }

    public List<Transaction> toEntity(List<TransactionDTO> accountDTOs) {
        return accountDTOs.stream()
                .map(accountDTO -> modelMapper.map(accountDTO, Transaction.class))
                .toList();
    }

    public List<TransactionDTO> toDto(List<Transaction> accounts) {
        return accounts.stream()
                .map(account -> modelMapper.map(account, TransactionDTO.class))
                .toList();
    }
}
