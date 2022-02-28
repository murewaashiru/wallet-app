package com.example.wallet.reversal.repo;

import com.example.wallet.reversal.entity.Balances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IBalanceRepo extends JpaRepository<Balances,String> {
    Balances findByAccountNumber(String accountNumber);

//    void updateBalance(String accountNumber);
}
