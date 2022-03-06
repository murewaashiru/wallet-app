package com.example.wallet.repo;

import com.example.wallet.entity.Balances;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
//@RepositoryRestResource(collectionResourceRel = "Balance", path = "Balance")
//@Tag(name="Balance", description = "Wallet Balance API API")

public interface IBalanceRepo extends JpaRepository<Balances,String> {
    Balances findByAccountNumber(String accountNumber);

}
