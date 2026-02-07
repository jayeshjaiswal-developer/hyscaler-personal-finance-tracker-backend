package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
