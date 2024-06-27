package com.bank.service.impl;

import com.bank.entity.Account;
import com.bank.dto.AccountDTO;
import com.bank.enums.AccountStatus;
import com.bank.mapper.AccountMapper;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }


    @Override
    public void createNewAccount(AccountDTO accountDTO) {

        accountDTO.setCreationDate(new Date());
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        //save into the database(repository)
        accountRepository.save(accountMapper.convertToEntity(accountDTO));
    }

    @Override
    public List<AccountDTO> listAllAccounts() {
        //we are getting the list of account but we need to return list of AccountDTO
        List<Account> accountList = accountRepository.findAll();
        //we need to convert list of entity to the list of dtos
        return accountList.stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        //find the account belongs the id
        Account account = accountRepository.findById(id).get();
        //set status to deleted
        account.setAccountStatus(AccountStatus.DELETED);
        //save the updated account object
        accountRepository.save(account);
    }

    @Override
    public void activateAccount(Long id) {
        //find the account belongs the id
        Account account = accountRepository.findById(id).get();
        //set status to active
        account.setAccountStatus(AccountStatus.ACTIVE);
        //save the updated account object
        accountRepository.save(account);
    }

    @Override
    public AccountDTO findById(Long id) {
        //find the account based on id, then convert it dto and return it
        return accountMapper.convertToDTO(accountRepository.findById(id).get());
    }

    @Override
    public List<AccountDTO> listAllActiveAccounts() {

        //list of active accounts from the repository
        List<Account> accountList = accountRepository.findAllByAccountStatus(AccountStatus.ACTIVE);
        //convert active accounts to accountDtos and return it
        return accountList.stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void updateAccount(AccountDTO accountDTO) {
        accountRepository.save(accountMapper.convertToEntity(accountDTO));
    }

}
