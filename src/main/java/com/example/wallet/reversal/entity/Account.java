package com.example.wallet.reversal.entity;

import javax.persistence.*;

@Entity
@Table(name="WLT_T_ACCOUNT_DETAILS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "bvn", nullable = false)
    private String bvn;
}
