package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Double transactionAmount;
    private String transactionDescription;
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    private Expense expenseId;

    @ManyToOne
    private IncomeSource incomeSourceId;

    @ManyToOne private User user;
}

