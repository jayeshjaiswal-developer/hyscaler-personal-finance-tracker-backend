package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ApiResponseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.IncomeSourceDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.enums.Frequency;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.enums.TransactionType;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Expense;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.IncomeSource;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Transaction;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.IncomeSourceRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.TransactionRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.UserRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.util.IncomeSourceUtil;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.util.JwtUtil;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeSourceService {

    private final IncomeSourceRepository incomeSourceRepository;
    private final ModelMapper mapper;
    private final JwtUtil jwtUtil;
    private  final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public ResponseEntity<ApiResponseDTO<IncomeSource>> addSource(IncomeSourceDTO incomeSourceDTO, String token) {
        System.out.println("Method invoked: addSource() in IncomeSourceService");
        LocalDate today = LocalDate.now();
        IncomeSource incomeSource = mapper.map(incomeSourceDTO, IncomeSource.class);
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);
        incomeSource.setUser(user);
        incomeSource.setNextExecutionDate(IncomeSourceUtil.calculateNextDate(today, incomeSource.getSourceFrequency()));
        IncomeSource savedIncomeSource =  incomeSourceRepository.save(incomeSource);

        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(incomeSource.getSourceAmount());
        transaction.setTransactionDescription(incomeSource.getSourceTitle());
        transaction.setTransactionDate(today);
        transaction.setTransactionType(TransactionType.INCOME);
        transaction.setIncomeSourceId(incomeSource);
        transaction.setUser(incomeSource.getUser());
        transactionRepository.save(transaction);


        return  ResponseEntity.status(201).body(new ApiResponseDTO<>("Income Source Added Successfully", savedIncomeSource));
    }


    public List<IncomeSource> getSourceByUser(String token){
            String email = jwtUtil.extractUsername(token);
            User user = userRepository.findByEmail(email);
            List<IncomeSource> sourcesList = incomeSourceRepository.findByUser(user);
            System.out.println(sourcesList);
            return  sourcesList;
    }
}
