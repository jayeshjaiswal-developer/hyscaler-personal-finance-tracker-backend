package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.util;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.enums.Frequency;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class IncomeSourceUtil {
    public static LocalDate calculateNextDate(LocalDate current, Frequency frequency){
        return switch (frequency){
            case DAILY -> current.plusDays(1);
            case WEEKLY -> current.plusWeeks(1);
            case MONTHLY -> current.plusMonths(1);
            case QUARTERLY -> current.plusMonths(3);
            case YEARLY -> current.plusYears(1);
        };
    }
}
