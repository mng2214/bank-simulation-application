package com.bank.repository;

import com.bank.dto.AccountDTO;
import com.bank.exception.RecordNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {

    // class will be representing db
    public static List<AccountDTO> accountDTOList = new ArrayList<>();

    public AccountDTO save(AccountDTO accountDTO) {
        accountDTOList.add(accountDTO);
        return accountDTO;
    }


    public List<AccountDTO> findAllAccounts() {
        return accountDTOList;
    }

    public AccountDTO findById(UUID id) {
        return findAllAccounts().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("Account does not exist"));
    }
}
