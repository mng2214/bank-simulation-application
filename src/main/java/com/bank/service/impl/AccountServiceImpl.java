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
    public void createNewAccount(AccountDTO accountDTO) {
        AccountDTO accountDTO1 = new AccountDTO();
        AccountDTO saved = accountRepository.save(accountDTO1);
    }

    @Override
    public List<AccountDTO> listAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.findById(id).setAccountStatus(AccountStatus.DELETED);
    }

    @Override
    public void activateAccount(Long id) {
        accountRepository.findById(id).setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public List<AccountDTO> getSenderAccounts() {
        return null;
    }

    @Override
    public AccountDTO findById(Long id) {
        return accountRepository.findById(id);
    }


}
