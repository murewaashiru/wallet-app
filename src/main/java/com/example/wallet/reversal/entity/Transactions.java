package com.example.wallet.reversal.entity;

import javax.persistence.*;

@Entity
@Table(name="WLT_T_DAY2DAY_RECORDS")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "requestId", nullable = false)
    private String requestId;

    public Transactions(int id, String accountNumber, String requestId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.requestId = requestId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
