package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ApiResponseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.BudgetDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.IncomeSourceDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Budget;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.IncomeSource;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.BudgetRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.UserRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final ModelMapper mapper;
    private final JwtUtil jwtUtil;
    private  final UserRepository userRepository;

    public ResponseEntity<ApiResponseDTO<Budget>> addSource(BudgetDTO budgetDTO, String token) {
        System.out.println("Method invoked: addSource() in BudgetService");
        Budget budget = mapper.map(budgetDTO, Budget.class);
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);
        budget.setUser(user);
        budget.setBudgetSpentAmount(0.0);
        Budget savedBudget =  budgetRepository.save(budget);
        return  ResponseEntity.status(201).body(new ApiResponseDTO<>("Budget Added Successfully", savedBudget));
    }

    public List<Budget> getBudgetByUser(String token){
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);
        List<Budget> budgetsList = budgetRepository.findByUser(user);
        System.out.println(budgetsList);
        return  budgetsList;
    }
}
