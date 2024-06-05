package com.bank.service;

import com.bank.enums.AccountType;
import com.bank.dto.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId);

    List<Account> listAllAccounts();

    void deleteAccountById(UUID id);

    void activateAccount(UUID id);

    List<Account> getSenderAccounts();

    Account findById(UUID id);
}

