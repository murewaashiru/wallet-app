package com.example.wallet.repo;

import com.example.wallet.entity.Balances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IBalanceRepo extends JpaRepository<Balances,String> {
    Balances findByAccountNumber(String accountNumber);

}
