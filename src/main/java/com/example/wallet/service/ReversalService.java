package com.example.wallet.service;

import com.example.wallet.entity.Balances;
import com.example.wallet.entity.ReversalResponse;
import com.example.wallet.entity.Transactions;
import com.example.wallet.repo.IBalanceRepo;
import com.example.wallet.repo.ITransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReversalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReversalService.class);

    @Autowired
    private ITransactionRepo iTransactionRepo;
    @Autowired
    private IBalanceRepo iBalanceRepo;

    ReversalResponse reversalResponse;

    @Transactional
    public ReversalResponse debitAccount(Transactions transactions, String corebankingResponseId){
        LOGGER.info("Initiating debit for user account {}", transactions.getAccountNumber());
        Balances balance = iBalanceRepo.findByAccountNumber(transactions.getAccountNumber());

        float newAccBalance = balance.getBalance() - transactions.getAmount();
        balance.setBalance(newAccBalance);
        iBalanceRepo.save(balance);

        Transactions newTransaction = new Transactions();
        newTransaction.setRequestId(corebankingResponseId);
        newTransaction.setAmount(transactions.getAmount());
        newTransaction.setAccountNumber(transactions.getAccountNumber());
        newTransaction.setTransactionType("DEBIT");
        iTransactionRepo.save(newTransaction);

        new ReversalResponse("000", "Successful", corebankingResponseId, "Debit successful");
        LOGGER.info("Reversal response- {}", reversalResponse);
        return reversalResponse;
    }

    @Transactional
    public ReversalResponse creditAccount(Transactions transactions, String corebankingResponseId){
        LOGGER.info("Initiating credit for user account {}", transactions.getAccountNumber());
        Balances balance = iBalanceRepo.findByAccountNumber(transactions.getAccountNumber());

        float newAccBalance = balance.getBalance() + transactions.getAmount();
        balance.setBalance(newAccBalance);
        iBalanceRepo.save(balance);

        Transactions newTransaction = new Transactions();
        newTransaction.setRequestId(corebankingResponseId);
        newTransaction.setAmount(transactions.getAmount());
        newTransaction.setAccountNumber(transactions.getAccountNumber());
        newTransaction.setTransactionType("CREDIT");
        iTransactionRepo.save(newTransaction);

        new ReversalResponse("000", "Successful", corebankingResponseId, "Credit successful");
        LOGGER.info("Reversal response- {}", reversalResponse);
        return reversalResponse;
    }

    @Transactional
    public ReversalResponse reversal(Transactions toDebit, Transactions toCredit){
        LOGGER.info("Initiating reversal for ID {}", toDebit.getRequestId());
        String responseId = "REV-" + toDebit.getRequestId();
        debitAccount(toDebit, responseId);
        creditAccount(toCredit, responseId);

        reversalResponse = new ReversalResponse("000", "Successful", responseId, "Reversal successful for requestID " + toCredit.getRequestId() );
        return reversalResponse;
    }
}