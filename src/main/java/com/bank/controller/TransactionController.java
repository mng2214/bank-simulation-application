package com.bank.controller;

import com.bank.model.Transaction;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    AccountService accountService;
    TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String getMakeTransfer(Model model) {
        model.addAttribute("transaction", Transaction.builder().build());
        model.addAttribute("accounts",accountService.listAllAccounts());
        model.addAttribute("lastTransactions", transactionService.getLast10Transactions());
        return "transaction/make-transfer";
    }
}
