package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    private Map<String, String> otpStore = new HashMap<>();

    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpStore.put(email, otp);
        return otp;
    }

    public String sendOtp(UserDTO userDTO) {

        String otp = generateOtp(userDTO.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userDTO.getEmail());
        message.setSubject("Your One-Time Password (OTP) for Secure Login");

        String mailBody =
                "Hello "+userDTO.getName()+",\n\n" +
                        "Your One-Time Password (OTP) for login is:\n\n" +
                        otp + "\n\n" +
                        "This OTP is valid for 10 minutes. Please do not share it with anyone.\n\n" +
                        "If you didn’t request this OTP, please ignore this email.\n\n" +
                        "Thank You\n\n" +
                        "Jayesh Technologies Pvt. Ltd.\n" +
                        "G05, Electronic Complex,\n" +
                        "Indore (452010), Madhya Pradesh";

        message.setText(mailBody);

        mailSender.send(message);
        return otp;
    }

//    public boolean verifyOtp(String email, String otp) {
//        return otp.equals(otpStore.get(email));
//    }
}
