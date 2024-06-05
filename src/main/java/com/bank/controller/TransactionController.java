package com.bank.controller;

import com.bank.dto.Account;
import com.bank.dto.Transaction;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
public class TransactionController {

    private static final Logger logger = Logger.getLogger(AccountController.class.getName());

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
    public String completeTransfer(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accounts", accountService.listAllAccounts());
            model.addAttribute("lastTransactions", transactionService.last10Transactions());
            logger.warning(bindingResult.getAllErrors().toString());
            return "transaction/make-transfer";
        }
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
