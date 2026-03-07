package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.enums.Frequency;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class IncomeSourceDTO {
    private Long sourceId;
    private String sourceTitle;
    private Double sourceAmount;
    private Frequency sourceFrequency;
    private User user;
    private LocalDate nextExecutionDate;
}


