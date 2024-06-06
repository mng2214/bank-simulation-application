package com.bank.mapper;

import com.bank.Entity.Account;
import com.bank.dto.AccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountMapper(ModelMapper modalMapper) {
        this.modalMapper = modalMapper;
    }

    private final ModelMapper modalMapper;

    public Account toEntity(AccountDTO account) {
        return modalMapper.map(account, Account.class);
    }

    public AccountDTO toDto(Account account, Long id) {
        return modalMapper.map(account, AccountDTO.class);
    }






}
