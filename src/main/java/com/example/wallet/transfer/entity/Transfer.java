package com.example.wallet.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "TRANSFERS")
public class Transfer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String requestId;

    private String sourceAccountNumber;

    private String sourceAccountName;

//    private LocalDateTime transactionDate;

    private String destinationAccountNumber;

    private String destinationAccountName;

    private String destinationBankCode;

    private String senderNarration;

//    private String transactionType;

    private float amount;

}
