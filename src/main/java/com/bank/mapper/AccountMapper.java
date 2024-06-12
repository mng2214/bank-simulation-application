package com.bank.mapper;

import com.bank.Entity.Account;
import com.bank.dto.AccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountDTO convertToDTO(Account entity){
        //this method will accept Account entity and will convert it to DTO
        return modelMapper.map(entity,AccountDTO.class);
    }

    public Account convertToEntity(AccountDTO dto){
        //this method will accept dto and convert it to entity
        return modelMapper.map(dto,Account.class);
    }





}
