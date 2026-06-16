package com.adrianh.bank.banking_system.service;

import com.adrianh.bank.banking_system.model.User;
import com.adrianh.bank.banking_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public User getUserById(Long id) {
    return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
}

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}