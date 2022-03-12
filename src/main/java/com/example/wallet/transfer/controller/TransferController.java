package com.example.wallet.transfer.controller;

import com.example.wallet.entity.Balances;
import com.example.wallet.entity.ReversalResponse;
import com.example.wallet.transfer.entity.Transfer;
import com.example.wallet.transfer.entity.WalletAccount;
import com.example.wallet.transfer.repo.TransferRepo;
import com.example.wallet.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/create-account")
    public WalletAccount createNewWallet(@RequestBody WalletAccount walletAccount) {
        return transferService.createWallet(walletAccount);
    }

    @GetMapping("/getbalance/{accountnumber}")
    public Balances getBalance(@PathVariable String accountnumber){

        return transferService.getBalance(accountnumber);
    }

    @PutMapping("/update-account")
    public WalletAccount updateProduct (@RequestBody WalletAccount walletAccount) {
        return transferService.updateWallet(walletAccount);
    }

    @PostMapping("/transfer")
    public ResponseEntity<ReversalResponse> createTransfer (@RequestBody Transfer transferTransaction) {
        return new ResponseEntity<>(transferService.transfer(transferTransaction), HttpStatus.OK) ;
    }

}
