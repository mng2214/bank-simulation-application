package com.bank.service;

import com.bank.dto.AccountDTO;
import com.bank.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {

    void createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> listAllAccounts();

    void deleteAccountById(Long id);

    void activateAccount(Long id);

    List<AccountDTO> getSenderAccounts();

    AccountDTO findById(Long id);
}

