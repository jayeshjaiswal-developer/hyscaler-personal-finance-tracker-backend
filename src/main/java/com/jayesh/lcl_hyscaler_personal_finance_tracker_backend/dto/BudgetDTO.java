package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class BudgetDTO {
        private Long budgetId;
        private String budgetTitle;
        private Double budgetSpentAmount;
        private Double budgetTargetAmount;
        private User user;
}

