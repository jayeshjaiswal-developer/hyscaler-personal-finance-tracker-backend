package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Expense {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long expenseId;

        private String expenseTitle;
        private Double expenseAmount;
        private String expenseDescription;
        private LocalDate expenseDate;
        private Long budgetId;

        @ManyToOne
        private Budget expenseCategory;

        @ManyToOne
        private User user;
}
