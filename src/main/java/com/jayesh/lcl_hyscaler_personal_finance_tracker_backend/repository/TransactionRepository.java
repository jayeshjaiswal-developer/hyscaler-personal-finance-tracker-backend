package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Budget;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Transaction;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);

}
