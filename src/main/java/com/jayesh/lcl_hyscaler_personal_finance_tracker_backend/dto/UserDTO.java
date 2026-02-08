package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
        private Long id;
        private String name;
        private String phone;
        private String email;
        private String password;
        private Boolean isPhoneVerified;
        private Boolean isEmailVerified;
        private String otp;
        private LocalDateTime otp_createdAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}