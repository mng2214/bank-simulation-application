package com.bank.repository;

import com.bank.entity.Account;

import com.bank.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

        List<Account> findAllByAccountStatus(AccountStatus accountType);

}
