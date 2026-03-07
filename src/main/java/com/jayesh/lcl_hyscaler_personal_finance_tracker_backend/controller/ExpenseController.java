package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.controller;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ApiResponseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ExpenseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Expense;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

        @PostMapping("/add")
        public ResponseEntity<ApiResponseDTO<Expense>> addExpense(@RequestBody ExpenseDTO expenseDTO, @RequestHeader("Authorization") String authHeader){
            String token = authHeader.substring(7);
            System.out.println("Method Invoked: addExpense()");
            System.out.println(expenseDTO);
            return expenseService.addExpense(expenseDTO, token);
        }

        @GetMapping()
        public List<Expense> getExpenseByUser(@RequestHeader("Authorization") String authHeader){
            String token = authHeader.substring(7);
            return expenseService.getExpenseByUser(token);
        }
}
