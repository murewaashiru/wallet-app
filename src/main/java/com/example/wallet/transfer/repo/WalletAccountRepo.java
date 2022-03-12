package com.example.wallet.transfer.repo;

import com.example.wallet.transfer.entity.WalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletAccountRepo extends JpaRepository <WalletAccount, Long> {
    WalletAccount findByAccountNumber(String accountNumber);
}
