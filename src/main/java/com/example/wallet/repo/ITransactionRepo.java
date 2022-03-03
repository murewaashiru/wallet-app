package com.example.wallet.repo;

import com.example.wallet.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepo extends JpaRepository<Transactions,String> {
    List<Transactions> findByRequestId(String requestId);
//    Transactions create(Transactions transactions);
}
