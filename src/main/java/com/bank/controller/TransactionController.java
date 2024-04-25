package com.bank.controller;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        model.addAttribute("accounts", accountService.listAllAccounts());
        model.addAttribute("lastTransactions", transactionService.last10Transactions());
        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String completeTransfer(@ModelAttribute("transaction") Transaction transaction) {
        Account sender = accountService.findById(transaction.getSender());
        Account receiver = accountService.findById(transaction.getReceiver());
        BigDecimal amount = transaction.getAmount();
        Date createDate = new Date();
        String message = transaction.getMessage();
        transactionService.makeTransfer(sender, receiver, amount, createDate, message);
        return "redirect:/make-transfer";
    }

    @GetMapping("/transactions/{id}")
    public String getTransactions(@PathVariable UUID id, Model model) {
        List<Transaction> transactionListById = transactionService.findTransactionListById(id);
        model.addAttribute("transactions", transactionListById);
        return "transaction/transactions";
    }

}
