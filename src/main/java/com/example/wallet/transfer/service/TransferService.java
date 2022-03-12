package com.example.wallet.transfer.service;

import com.example.wallet.WalletApplication;
import com.example.wallet.controller.Reversal;
import com.example.wallet.entity.Balances;
import com.example.wallet.entity.ReversalResponse;
import com.example.wallet.entity.Transactions;
import com.example.wallet.repo.IBalanceRepo;
import com.example.wallet.repo.ITransactionRepo;
import com.example.wallet.transfer.entity.Transfer;
import com.example.wallet.transfer.entity.WalletAccount;
import com.example.wallet.transfer.repo.TransferRepo;
import com.example.wallet.transfer.repo.WalletAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import javax.transaction.Transactional;

@Service
public class TransferService {

    @Autowired
    private Transactions transactions;

    @Autowired
    private IBalanceRepo iBalanceRepo;

    @Autowired
    private ITransactionRepo iTransactionRepo;


    @Autowired
    private WalletAccountRepo walletAccountRepo;

    @Autowired
    private TransferRepo transferRepo;


    @Transactional
    public ReversalResponse transfer(Transfer transfer) {
        try {
            String responseString;
            float amount = transfer.getAmount();
            WalletAccount sourceAccount = walletAccountRepo.findByAccountNumber(transfer.getSourceAccountNumber());
            WalletAccount destinationAccount = walletAccountRepo.findByAccountNumber(transfer.getDestinationAccountNumber());
            if (destinationAccount == null || sourceAccount == null) {
                responseString = String.format("Please check account details and try again");
                return new ReversalResponse("900", responseString, "Cannot process transaction", "");
            } else if (sourceAccount.getAccountBalance() <= amount) {
                responseString = String.format("Insufficient Funds on %s's account, cannot proceed with transaction", sourceAccount.getCustomerName());
                return new ReversalResponse("900", responseString, "Cannot process transaction", "");
            }
            Transfer savedTransfer = createTransfer(transfer);
            sourceAccount.setAccountBalance(sourceAccount.getAccountBalance() - amount);
            destinationAccount.setAccountBalance(destinationAccount.getAccountBalance() + amount);
            iBalanceRepo.save(updateBalance(sourceAccount.getAccountNumber(), sourceAccount.getAccountBalance()));
            iBalanceRepo.save(updateBalance(destinationAccount.getAccountNumber(), destinationAccount.getAccountBalance()));
            iTransactionRepo.save(new Transactions(savedTransfer.getRequestId(), java.time.LocalDateTime.now(), sourceAccount.getAccountNumber(), amount, "DEBIT"));
            iTransactionRepo.save(new Transactions(savedTransfer.getRequestId(), java.time.LocalDateTime.now(), destinationAccount.getAccountNumber(), amount, "CREDIT"));
            responseString = String.format("%.2f successfully transferred between %s and %s", amount, sourceAccount.getCustomerName(), destinationAccount.getCustomerName());
            return new ReversalResponse("200", responseString, "Transaction successful", "");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReversalResponse("900", "Error encountered", "Transaction failed", "");
        }
    }

    public Transfer createTransfer(Transfer transfer) {
        return transferRepo.save(transfer);
    }

    public Balances updateBalance(String accountnumber, float amount) {
        Balances existingBalance = iBalanceRepo.findByAccountNumber(accountnumber);
        existingBalance.setBalance(amount);
        return existingBalance;
    }

    public WalletAccount updateWalletBalance(String accountnumber, float amount) {
        WalletAccount existingWalletAccount = walletAccountRepo.findByAccountNumber(accountnumber);
        existingWalletAccount.setAccountBalance(amount);
        return existingWalletAccount;
    }

    public Balances getBalance(String accountnumber) {
        return iBalanceRepo.findByAccountNumber(accountnumber);
    }

    public WalletAccount createWallet (WalletAccount walletAccount) {
            iBalanceRepo.save(new Balances(walletAccount.getAccountNumber(), walletAccount.getAccountBalance()));
            return walletAccountRepo.save(walletAccount);
    }

    public WalletAccount updateWallet (WalletAccount walletAccount) {
        WalletAccount existingWalletAccount = walletAccountRepo.findByAccountNumber(walletAccount.getAccountNumber());
        existingWalletAccount.setAccountBalance(walletAccount.getAccountBalance());
        updateBalance(existingWalletAccount.getAccountNumber(), existingWalletAccount.getAccountBalance());
        return walletAccountRepo.save(existingWalletAccount);
    }

}
