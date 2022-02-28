package com.example.wallet.reversal.service;

import com.example.wallet.reversal.entity.ReversalResponse;
import com.example.wallet.reversal.entity.Transactions;
import com.example.wallet.reversal.entity.Balances;
import com.example.wallet.reversal.repo.IBalanceRepo;
import com.example.wallet.reversal.repo.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReversalService {
    @Autowired
    private ITransactionRepo iTransactionRepo;
    @Autowired
    private IBalanceRepo iBalanceRepo;

    ReversalResponse reversalResponse;

    @Transactional
    public Transactions debitAccount(Transactions transactions, String requestId){
        System.out.println("-----START DEBIT---");
        Balances balance = iBalanceRepo.findByAccountNumber(transactions.getAccountNumber());
        float newAccBalance = balance.getBalance() - Float.parseFloat(transactions.getAmount());
        balance.setBalance(newAccBalance);
        iBalanceRepo.save(balance);
        System.out.println("1. Update the bal of Account "+ transactions.getAccountNumber() +" New balance is "+ balance.getBalance());

        transactions.setTransactionType("DEBIT");
        transactions.setRequestId(requestId);
        Transactions result = iTransactionRepo.save(transactions);
        System.out.println("2. Record the trnx - "+ iTransactionRepo.findByRequestId(requestId));

        System.out.println("-----END DEBIT-----");
//        reversalResponse = new ReversalResponse("000", "Successful", requestId, "Debit for transaction");
        return result;
    }

    @Transactional
    public Transactions creditAccount(Transactions transactions, String requestId){
        System.out.println("-----START CREDIT---");
        Balances balance = iBalanceRepo.findByAccountNumber(transactions.getAccountNumber());
        float newAccBalance = balance.getBalance() + Float.parseFloat(transactions.getAmount());
        balance.setBalance(newAccBalance);
        iBalanceRepo.save(balance);
        System.out.println("1. Update the bal of Account "+ transactions.getAccountNumber() +" New balance is "+ balance.getBalance());


        transactions.setTransactionType("CREDIT");
        transactions.setRequestId(requestId);
        Transactions result = iTransactionRepo.save(transactions);
        System.out.println("2. Record the trnx - "+ iTransactionRepo.findByRequestId(requestId));

        System.out.println("-----END CREDIT---");
        return result;
    }

    @Transactional
    public String reversal(Transactions toDebit, Transactions toCredit){
        System.out.println("-----START REVERSAL---");
        String responseId = "REV-" + Math.random();
        debitAccount(toDebit, responseId);
        creditAccount(toCredit, responseId);

        System.out.println("-----END REVERSAL---");
        reversalResponse = new ReversalResponse("000", "Successful", responseId, "Reversal successful for requestID" + toCredit.getRequestId() );
        return reversalResponse.toString();
    }
}