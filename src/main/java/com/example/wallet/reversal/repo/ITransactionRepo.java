package com.example.wallet.reversal.repo;

import com.example.wallet.reversal.entity.ReversalRequestDTO;
import com.example.wallet.reversal.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepo extends JpaRepository<Transactions,String> {
    Transactions findByRequestId(String accountNumber);
}
