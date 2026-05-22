package com.adrianh.bank.banking_system.service;

import com.adrianh.bank.banking_system.model.Account;
import com.adrianh.bank.banking_system.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account createAccount(Account account) {
        return repo.save(account);
    }

    public List<Account> getAccounts() {
        return repo.findAll();
    }
}