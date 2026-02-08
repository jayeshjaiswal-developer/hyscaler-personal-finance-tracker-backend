package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.controller;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ApiResponseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.UserDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping()
    public  ResponseEntity<ApiResponseDTO<UserDTO>> registerUser(@RequestBody UserDTO userDTO){
        System.out.println("Method invoked: registerUser()");
        System.out.println(userDTO);
        UserDTO responseDTO = userService.registerUser(userDTO);
        if(responseDTO==null) return ResponseEntity.status(409).body(new ApiResponseDTO<>("Email already exists", responseDTO));
        else return ResponseEntity.status(201).body(new ApiResponseDTO<>("User created successfully", responseDTO));
    }
}
