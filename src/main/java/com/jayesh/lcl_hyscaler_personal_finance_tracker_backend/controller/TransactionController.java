package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.controller;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Budget;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Transaction;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.BudgetService;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping()
    public List<Transaction> getBudgetByUser(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        return transactionService.getTransactionByUser(token);
    }
}
