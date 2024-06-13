package com.bank.repository;

import com.bank.Entity.Account;

import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

        List<Account> findAllByAccountStatus(AccountStatus accountType);

}
