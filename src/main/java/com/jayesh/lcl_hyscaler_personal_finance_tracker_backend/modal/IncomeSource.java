package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.enums.Frequency;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class IncomeSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sourceId;
    private String sourceTitle;
    private Double sourceAmount;

    @Enumerated(EnumType.STRING)
    private Frequency sourceFrequency;

    @ManyToOne
    private User user;
    private LocalDate nextExecutionDate;

}
