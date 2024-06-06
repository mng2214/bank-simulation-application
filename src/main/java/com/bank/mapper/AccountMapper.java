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



    public Account toEntity(AccountDTO account) {
        return modelMapper.map(account, Account.class);
    }

    public AccountDTO toDto(Account account, Long id) {
        return modelMapper.map(account, AccountDTO.class);
    }

    public List<Account> toEntity(List<AccountDTO> accountDTOs) {
        return accountDTOs.stream()
                .map(accountDTO -> modelMapper.map(accountDTO, Account.class))
                .toList();
    }

    public List<AccountDTO> toDto(List<Account> accounts) {
        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .toList();
    }





}
