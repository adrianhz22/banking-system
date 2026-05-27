package com.adrianh.bank.banking_system.controller;

import com.adrianh.bank.banking_system.dto.TransferRequest;
import com.adrianh.bank.banking_system.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final AccountService accountService;

    public TransferController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public String transfer(@RequestBody TransferRequest request) {

        accountService.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount()
        );

        return "Transferencia realizada correctamente.";
    }
}