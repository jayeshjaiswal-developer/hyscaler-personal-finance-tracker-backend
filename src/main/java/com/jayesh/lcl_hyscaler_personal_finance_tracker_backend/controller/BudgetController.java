package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.controller;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ApiResponseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.BudgetDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.IncomeSourceDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Budget;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.IncomeSource;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponseDTO<Budget>> addSource(@RequestBody BudgetDTO budgetDTO, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        System.out.println("Method Invoked: addBudget()");
        System.out.println(budgetDTO);
        return budgetService.addSource(budgetDTO, token);
    }

    @GetMapping()
    public List<Budget> getBudgetByUser(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        return budgetService.getBudgetByUser(token);
    }

}
