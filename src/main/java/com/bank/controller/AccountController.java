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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
public class AccountController {
    private static final Logger logger = Logger.getLogger(AccountController.class.getName());
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
    public String getCreateFormPage(Model model) {
        model.addAttribute("account", Account.builder().build());
        model.addAttribute("accountTypes", AccountType.values());
        return "account/create-account";
    }

    @PostMapping("/create")
    public String saved(@ModelAttribute("account") Account account) {
        System.out.println(account);
        Account newAccount = accountService.createNewAccount(account.getBalance(), new Date(), account.getAccountType(), account.getUserId());
        System.out.println(newAccount);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") UUID id) {
        logger.info("Deleted Account UD : " + id);
        accountService.deleteAccountById(id);
        return "redirect:/index";
    }
}
