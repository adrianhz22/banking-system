package com.adrianh.bank.banking_system.controller;

import com.adrianh.bank.banking_system.model.Transaction;
import com.adrianh.bank.banking_system.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository repo;

    public TransactionController(TransactionRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return repo.findAll();
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getByAccount(
            @PathVariable Long accountId) {
        return repo.findByFromAccountIdOrToAccountIdOrderByCreatedAtDesc(
                accountId,
                accountId);
    }
}