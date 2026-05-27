package com.adrianh.bank.banking_system.service;

import com.adrianh.bank.banking_system.model.Account;
import com.adrianh.bank.banking_system.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void transfer(Long fromId, Long toId, double amount) {

        Account from = repo.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));

        Account to = repo.findById(toId)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        if (from.getBalance() < amount) {
            throw new RuntimeException("Saldo insuficiente");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        repo.save(from);
        repo.save(to);
    }
}