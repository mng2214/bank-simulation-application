package com.bank.repository;

import com.bank.Entity.Account;
import com.bank.dto.AccountDTO;
import com.bank.exception.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    // class will be representing db
//    public static List<AccountDTO> accountDTOList = new ArrayList<>();
//
//    public AccountDTO save(AccountDTO accountDTO) {
//        accountDTOList.add(accountDTO);
//        return accountDTO;
//    }
//
//
//    public List<AccountDTO> findAllAccounts() {
//        return accountDTOList;
//    }
//
//    public AccountDTO findById(Long id) {
//        return findAllAccounts().stream()
//                .filter(account -> account.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RecordNotFoundException("Account does not exist"));
//    }


}
