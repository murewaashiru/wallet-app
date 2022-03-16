package com.example.wallet.controller;

import com.example.wallet.entity.ReversalRequest;
import com.example.wallet.entity.ReversalResponse;
import com.example.wallet.entity.Transactions;
import com.example.wallet.repo.ITransactionRepo;
import com.example.wallet.service.ReversalService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@Tag(name="Reversal", description = "API to reverse transactions")
public class Reversal {
    private static final Logger LOGGER = LoggerFactory.getLogger(Reversal.class);

    @Autowired
    private ReversalService reversalService;
    @Autowired
    private ITransactionRepo iTransactionRepo;

    ReversalResponse reversalResponse;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reversal")
    @Transactional
    public ResponseEntity<ReversalResponse> createReversals(@RequestBody ReversalRequest reversalRequest) {
        List<Transactions> getTransaction = iTransactionRepo.findByRequestId(reversalRequest.getRequestId());
        LOGGER.info("Lookup transaction for ID {}", reversalRequest.getRequestId());
        List<Transactions> getReversedTransaction = iTransactionRepo.findByRequestId("REV-"+reversalRequest.getRequestId());
        LOGGER.info("Lookup reversed transaction for ID {}", "REV-"+reversalRequest.getRequestId());

        if (getTransaction.size() == 0) {
            reversalResponse = new ReversalResponse("900", "Failed", null, "Transaction not found");
            return ResponseEntity.ok().body(reversalResponse);
        }
        if (getReversedTransaction.size() != 0) {
            reversalResponse = new ReversalResponse("900", "Failed", null, "Transaction has already been reversed");
            return ResponseEntity.ok().body(reversalResponse);
        }

        // To determine what account to debit or credit
        Transactions toDebit = getTransaction.get(1), toCredit = getTransaction.get(0);
        if (getTransaction.get(0).getTransactionType() == "DEBIT"){
            toCredit = getTransaction.get(0);
            toDebit = getTransaction.get(1);
        }

        ReversalResponse result = reversalService.reversal(toDebit, toCredit);
        LOGGER.info("Reversal response- {}", result);
        return ResponseEntity.ok().body(result);
    }
}
