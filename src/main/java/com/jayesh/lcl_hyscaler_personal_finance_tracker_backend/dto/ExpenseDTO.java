package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Budget;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDTO {
    private Long expenseId;
    private String expenseTitle;
    private Double expenseAmount;
    private String expenseDescription;
    private LocalDate expenseDate;
    private Long budgetId;
    private Budget expenseCategory;
    private User user;
}

