package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
