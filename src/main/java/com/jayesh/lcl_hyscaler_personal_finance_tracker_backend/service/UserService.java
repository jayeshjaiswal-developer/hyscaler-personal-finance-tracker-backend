package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ApiResponseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.UserDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.UserRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
//    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final OtpService otpService;





    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserDTO registerUser(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        if(userRepository.findByEmail(user.getEmail())!=null){
            System.out.println("Email Already Exists");
            return null;
        }else {
            User savedUser = userRepository.save(user);
//            otpService.sendOtp(user.getEmail());
//            System.out.println("Otp sent to mail");
            return mapper.map(savedUser, UserDTO.class);
        }
    }

    public String loginUser(UserDTO userDTO){
        System.out.println("Method invoked: loginUser() in UserService.java");
        User user = mapper.map(userDTO, User.class);
        User dbUser = userRepository.findByEmail(user.getEmail());
        String otpStatus ="";
        if(dbUser!=null) {
            System.out.println("User found");
            if(passwordEncoder.matches(user.getPassword(),dbUser.getPassword())){
                System.out.println("Password Mathces");
//                token = jwtUtil.generateToken(user.getEmail());
                String otp = otpService.sendOtp(mapper.map(dbUser,UserDTO.class));
                dbUser.setOtp(passwordEncoder.encode(otp));
                dbUser.setOtp_createdAt(LocalDateTime.now());
                userRepository.save(dbUser);
                otpStatus="OTP sent to registered email address";
            }else System.out.println("Incorrect Password");
        }
        else System.out.println("User not found");
        return otpStatus;
        }

    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
//        System.out.println(email);
//        System.out.println(otp);
        User dbUser = userRepository.findByEmail(email);
        boolean isOTPValid = passwordEncoder.matches(otp,dbUser.getOtp());
        if (isOTPValid){
            String token = jwtUtil.generateToken(dbUser.getEmail());
            String message =  "OTP Verified Successfully";
            return Map.of(
                    "token", token,
                    "message", message,
                    "name", dbUser.getName(),
                    "email", dbUser.getEmail()
            );
        }
        else return null;
    }
}
