package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class ApiResponseDTO<T> {
    private final String message;
    private final T data;
}
