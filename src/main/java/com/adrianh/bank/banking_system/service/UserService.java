package com.adrianh.bank.banking_system.service;

import com.adrianh.bank.banking_system.model.User;
import com.adrianh.bank.banking_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo,
            PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User createUser(User user) {

        user.setPassword(
                encoder.encode(user.getPassword()));

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