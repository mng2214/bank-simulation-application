package com.bank.controller;

import com.bank.enums.AccountType;
import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import com.bank.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String getIndexPage(Model model) {
        model.addAttribute("accountList", accountService.listAllAccounts());
        return "account/index";
    }

    @GetMapping("/create-form")
    public String getCreateFormPage(Model model){
        model.addAttribute("account", Account.builder().build());
        model.addAttribute("accountTypes", AccountType.values());
        return "account/create-account";
    }

}
