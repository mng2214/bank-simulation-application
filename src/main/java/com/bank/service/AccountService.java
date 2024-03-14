package com.bank.service;

import com.bank.enums.AccountType;
import com.bank.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {
    Account createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId);
    List<Account> listAllAccounts();

}
