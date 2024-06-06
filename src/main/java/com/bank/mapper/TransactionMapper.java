package com.bank.mapper;

import com.bank.Entity.Transaction;
import com.bank.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionMapper(ModelMapper modalMapper) {
        this.modalMapper = modalMapper;
    }

    private final ModelMapper modalMapper;

    public Transaction toEntity(TransactionDTO account) {
        return modalMapper.map(account, Transaction.class);
    }

    public TransactionDTO toDto(Transaction account, Long id) {
        return modalMapper.map(account, TransactionDTO.class);
    }
}
