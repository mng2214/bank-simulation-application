package com.bank.service.impl;

import com.bank.dto.AccountDTO;
import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId) {

        // create account object
        AccountDTO accountDTO = AccountDTO.builder()
                .id(UUID.randomUUID())
                .balance(balance)
                .accountType(accountType)
                .userId(userId)
                .creationDate(createDate)
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        // save into db
        AccountDTO saved = accountRepository.save(accountDTO);

        //return onj
        return saved;
    }

    @Override
    public List<AccountDTO> listAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    @Override
    public void deleteAccountById(UUID id) {
        accountRepository.findById(id).setAccountStatus(AccountStatus.DELETED);
    }

    @Override
    public void activateAccount(UUID id) {
        accountRepository.findById(id).setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public List<AccountDTO> getSenderAccounts() {
        return null;
    }

    @Override
    public AccountDTO findById(UUID id) {
        return accountRepository.findById(id);
    }


}
