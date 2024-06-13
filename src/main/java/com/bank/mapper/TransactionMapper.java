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

    public TransactionDTO convertToDTO(Transaction entity){
        //this method will accept Account entity and will convert it to DTO
        return modelMapper.map(entity,TransactionDTO.class);
    }

    public Transaction convertToEntity(TransactionDTO dto){
        //this method will accept dto and convert it to entity
        return modelMapper.map(dto,Transaction.class);
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
