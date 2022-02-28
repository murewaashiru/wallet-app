package com.example.wallet.reversal.controller;

import com.example.wallet.reversal.entity.ReversalResponse;
import com.example.wallet.reversal.entity.Transactions;
import com.example.wallet.reversal.entity.ReversalRequest;
import com.example.wallet.reversal.repo.ITransactionRepo;
import com.example.wallet.reversal.service.ReversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/reversal")
public class Reversal {
    @Autowired
    private ReversalService reversalService;
    @Autowired
    private ITransactionRepo iTransactionRepo;

    ReversalResponse reversalResponse;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    @Transactional
    public String createReversal(@RequestBody ReversalRequest reversalRequest) {
        Transactions toDebit, toCredit;
        List<Transactions> getTransaction = iTransactionRepo.findByRequestId(reversalRequest.getRequestId());
        if (getTransaction.size() == 0) {
            reversalResponse = new ReversalResponse("900", "Failed", null, "Transaction not found");
            return reversalResponse.toString();
        }

        if (getTransaction.get(0).getTransactionType() == "DEBIT"){
            toCredit = getTransaction.get(0);
            toDebit = getTransaction.get(1);
        } else {
            toCredit = getTransaction.get(1);
            toDebit = getTransaction.get(0);
        }

        String result = reversalService.reversal(toDebit, toCredit);
        System.out.println("Final result" + result);
        return result;

//        reversalResponse = new ReversalResponse("900", "Failed", null, "Reversal failed for requestID" + reversalRequest.getRequestId());
//        return reversalResponse.toString();
    }
}
