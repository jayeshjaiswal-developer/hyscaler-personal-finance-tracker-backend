package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long budgetId;

    private String budgetTitle;
    private Double budgetSpentAmount;
    private Double budgetTargetAmount;

    @ManyToOne
    private User user;
}
