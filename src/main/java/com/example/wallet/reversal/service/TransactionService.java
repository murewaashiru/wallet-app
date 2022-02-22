package com.example.wallet.reversal.service;

import com.example.wallet.reversal.entity.ReversalRequestDTO;
import com.example.wallet.reversal.entity.Transactions;
import com.example.wallet.reversal.repo.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private ITransactionRepo repository;

    public Transactions findByRequestId(String requestId) {
        return repository.findByRequestId(requestId);
    }
}