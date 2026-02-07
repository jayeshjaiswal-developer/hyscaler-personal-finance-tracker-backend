package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.controller;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUsers(){
        System.out.println("Method Invok");
        return userService.getAllUsers();
    }

}
