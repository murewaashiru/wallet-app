package com.example.wallet.transfer.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "WLT_T_ACCOUNT_DETAILS")
@Entity
public class WalletAccount {

    @Id
    private String accountNumber;


    private String customerName;


    private String BVN;


    private String email;

    private float accountBalance;

}
