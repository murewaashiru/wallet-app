package com.example.wallet.controller;

import com.example.wallet.entity.Transactions;
import com.example.wallet.repo.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class Transaction {
    @Autowired
    private ITransactionRepo iTransactionRepo;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Transactions> getTransactions() {
        return iTransactionRepo.findAll();
    }
}
