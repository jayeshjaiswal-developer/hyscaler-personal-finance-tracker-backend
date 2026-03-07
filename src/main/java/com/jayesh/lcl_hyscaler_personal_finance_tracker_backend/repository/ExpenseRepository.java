package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Expense;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);

}
