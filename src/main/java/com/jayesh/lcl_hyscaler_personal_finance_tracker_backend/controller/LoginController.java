package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.controller;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ApiResponseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.UserDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.OtpService;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.UserService;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final OtpService otpService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<String>> login(@RequestBody UserDTO userDTO){
        System.out.println("Method Invoked: login()");
        String token = userService.loginUser(userDTO);
        if(token=="") return ResponseEntity.status(401).body(new ApiResponseDTO<>("Invalid credentials", token));
        else return ResponseEntity.status(200).body(new ApiResponseDTO<>("OTP sent successfully", token));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponseDTO<Map<String, String>>> verifyOtp(@RequestBody Map<String, String > request) {
        System.out.println("Method invoked: verifyOtp()");
        Map<String, String> map = userService.verifyOtp(request);
//        System.out.println(data);
        if(map!=null) return ResponseEntity.status(200).body(new ApiResponseDTO<>("Login successfull", map));
        else return ResponseEntity.status(401).body(new ApiResponseDTO<>("Invalid OTP", map));

    }
}
