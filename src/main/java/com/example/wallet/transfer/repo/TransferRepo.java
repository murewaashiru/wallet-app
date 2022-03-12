package com.example.wallet.transfer.repo;

import com.example.wallet.transfer.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TransferRepo extends JpaRepository <Transfer, String> {
}
