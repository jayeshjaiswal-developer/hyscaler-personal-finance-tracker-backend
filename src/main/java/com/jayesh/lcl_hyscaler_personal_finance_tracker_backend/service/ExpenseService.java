package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ApiResponseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.BudgetDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ExpenseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.enums.TransactionType;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Budget;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Expense;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Transaction;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.BudgetRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.ExpenseRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.TransactionRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.UserRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
       private final ExpenseRepository expenseRepository;
        private final ModelMapper mapper;
        private final JwtUtil jwtUtil;
        private  final UserRepository userRepository;
        private final BudgetRepository budgetRepository;
        private final TransactionRepository transactionRepository;


        public ResponseEntity<ApiResponseDTO<Expense>> addExpense(ExpenseDTO expenseDTO, String token) {
            System.out.println("Method invoked: addExpense() in BudgetService");
            Expense expense = mapper.map(expenseDTO, Expense.class);
            String email = jwtUtil.extractUsername(token);
            User user = userRepository.findByEmail(email);
            Budget budget = budgetRepository.findById(expenseDTO.getBudgetId()).orElse(null);
            Double availableLimit = budget.getBudgetTargetAmount()- budget.getBudgetSpentAmount();

            if(expense.getExpenseAmount()<=availableLimit){
//                LocalDate today = LocalDate.now();
                expense.setUser(user);
                expense.setExpenseCategory(budget);
                Expense savedExpense = expenseRepository.save(expense);
                budget.setBudgetSpentAmount(budget.getBudgetSpentAmount() + expense.getExpenseAmount());
                budgetRepository.save(budget);

                Transaction transaction = new Transaction();
                transaction.setTransactionAmount(expense.getExpenseAmount());
                transaction.setTransactionDescription(expense.getExpenseDescription());
                transaction.setTransactionDate(expense.getExpenseDate());
                transaction.setTransactionType(TransactionType.EXPENSE);
                transaction.setExpenseId(savedExpense);
                transaction.setUser(expense.getUser());
                transactionRepository.save(transaction);
                return  ResponseEntity.status(201).body(new ApiResponseDTO<>("Expense Added Successfully", savedExpense));
            }else
                return ResponseEntity.status(400).body(new ApiResponseDTO<>("Expense Amount is more then available limit", null));
        }

        public List<Expense> getExpenseByUser(String token){
            String email = jwtUtil.extractUsername(token);
            User user = userRepository.findByEmail(email);
            List<Expense> expensesList = expenseRepository.findByUser(user);
            System.out.println(expensesList);
            return  expensesList;
        }
}
