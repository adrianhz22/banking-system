package com.adrianh.bank.banking_system.service;

import com.adrianh.bank.banking_system.dto.LoginRequest;
import com.adrianh.bank.banking_system.dto.LoginResponse;
import com.adrianh.bank.banking_system.model.User;
import com.adrianh.bank.banking_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = generateFakeToken(user);

        return new LoginResponse(
                token,
                user.getId(),
                user.getName());
    }

    private String generateFakeToken(User user) {
        return "token_" + user.getId() + "_" + System.currentTimeMillis();
    }
}