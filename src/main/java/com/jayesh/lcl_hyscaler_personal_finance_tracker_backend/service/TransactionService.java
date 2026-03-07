package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Budget;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Transaction;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.BudgetRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.TransactionRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.UserRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper mapper;
    private final JwtUtil jwtUtil;
    private  final UserRepository userRepository;

    public List<Transaction> getTransactionByUser(String token){
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);
        List<Transaction> transactionsList = transactionRepository.findByUser(user);
        System.out.println(transactionsList);
        return  transactionsList;
    }
}
