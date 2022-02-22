package com.example.wallet.reversal.entity;

import javax.persistence.*;

@Entity
@Table(name="WLT_T_WALLET_BALANCES")
public class Balances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private double balance;
}
