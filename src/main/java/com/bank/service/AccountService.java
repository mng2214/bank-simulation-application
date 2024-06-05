package com.bank.service;

import com.bank.dto.AccountDTO;
import com.bank.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountDTO createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId);

    List<AccountDTO> listAllAccounts();

    void deleteAccountById(UUID id);

    void activateAccount(UUID id);

    List<AccountDTO> getSenderAccounts();

    AccountDTO findById(UUID id);
}

