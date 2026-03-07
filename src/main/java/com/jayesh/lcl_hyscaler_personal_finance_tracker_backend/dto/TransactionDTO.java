package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.enums.TransactionType;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Expense;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.IncomeSource;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    private Long transactionId;
    private Double transactionAmount;
    private String transactionDescription;
    private LocalDate transactionDate;
    private TransactionType transactionType;
    private Expense expense;
    private IncomeSource incomeSource;
    private User user;
}
