package com.bank.controller;

import com.bank.dto.AccountDTO;
import com.bank.enums.AccountType;
import com.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
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
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("accountTypes", AccountType.values());
        return "account/create-account";
    }

    @PostMapping("/create")
    public String saved(@Valid @ModelAttribute("account") AccountDTO accountDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accountTypes", AccountType.values());
            logger.warning(bindingResult.getAllErrors().toString());
            return "account/create-account";
        }
        logger.info(accountDTO.toString());
        accountService.createNewAccount(accountDTO);
        //logger.info(newAccountDTO.toString());
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        logger.info("Deleted Account UD : " + id);
        accountService.deleteAccountById(id);
        return "redirect:/index";
    }

    @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable("id") Long id) {
        logger.info("Activated Account UD : " + id);
        accountService.activateAccount(id);
        return "redirect:/index";
    }
}
