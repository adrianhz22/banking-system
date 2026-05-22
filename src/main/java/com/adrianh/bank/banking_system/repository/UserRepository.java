package com.adrianh.bank.banking_system.repository;

import com.adrianh.bank.banking_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}