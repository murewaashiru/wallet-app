package com.example.wallet.reversal.controller;

import com.example.wallet.reversal.entity.Account;
import com.example.wallet.reversal.entity.ReversalRequestDTO;
import com.example.wallet.reversal.entity.ReversalResponseDTO;
import com.example.wallet.reversal.entity.Transactions;
import com.example.wallet.reversal.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reversal")
public class Reversal {
    @Autowired
    private TransactionService transactionService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createReversal(@RequestBody Transactions transactions) {
//        public ReversalResponseDTO createReversal(@RequestBody ReversalRequestDTO reversalRequestDTO) {
        System.out.println(transactionService.findByRequestId(transactions.getRequestId()));

        // TODOS
        // 1. Check if account transactions exist based on transaction id
        // 2. Reverse the transaction.
        // a. Implement DB transaction
        // b. Check if

    }
}
