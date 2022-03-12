package com.example.wallet.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Data
@Entity
@Table(name="WLT_T_DAY2DAY_RECORDS")
public class Transactions {
//    private static final DecimalFormat dfZero = new DecimalFormat("0.00");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "request_id", nullable = false)
    private String requestId;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column
    private LocalDateTime transactionDate;

    public Transactions() {
    }

    public Transactions(int id, String requestId, String accountNumber, float amount, String transactionType) {
        this.id = id;
        this.requestId = requestId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Transactions(String requestId, LocalDateTime date, String accountNumber, float amount, String transactionType) {
        this.requestId = requestId;
        this.transactionDate = date;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
