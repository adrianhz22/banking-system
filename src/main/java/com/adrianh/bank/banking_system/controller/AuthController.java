package com.adrianh.bank.banking_system.controller;

import com.adrianh.bank.banking_system.dto.LoginRequest;
import com.adrianh.bank.banking_system.dto.LoginResponse;
import com.adrianh.bank.banking_system.dto.RegisterRequest;
import com.adrianh.bank.banking_system.model.User;
import com.adrianh.bank.banking_system.service.AuthService;
import com.adrianh.bank.banking_system.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService,
            UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userService.createUser(user);
    }
}