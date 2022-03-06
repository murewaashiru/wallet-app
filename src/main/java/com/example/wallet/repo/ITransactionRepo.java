package com.example.wallet.repo;

import com.example.wallet.entity.Transactions;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RepositoryRestResource(collectionResourceRel = "Transactions", path = "Transactions")
//@Tag(name="Transactions", description = "Transactions API API")

public interface ITransactionRepo extends JpaRepository<Transactions,String> {
    List<Transactions> findByRequestId(String requestId);
}
