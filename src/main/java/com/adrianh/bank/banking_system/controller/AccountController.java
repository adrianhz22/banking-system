package com.adrianh.bank.banking_system.controller;

import com.adrianh.bank.banking_system.model.Account;
import com.adrianh.bank.banking_system.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @GetMapping
    public List<Account> getAll() {
        return service.getAccounts();
    }
}