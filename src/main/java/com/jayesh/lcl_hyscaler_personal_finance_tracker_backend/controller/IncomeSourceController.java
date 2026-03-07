package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.controller;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.ApiResponseDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.IncomeSourceDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.dto.TestDTO;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.IncomeSource;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.User;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.IncomeSourceService;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/income-sources")
public class IncomeSourceController {

    @Autowired
    private IncomeSourceService incomeSourceService;

    @PostMapping("/add")
        public ResponseEntity<ApiResponseDTO<IncomeSource>> addSource(@RequestBody IncomeSourceDTO incomeSourceDTO,  @RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        System.out.println("Method Invoked: addSource()");
        System.out.println(incomeSourceDTO);
        return incomeSourceService.addSource(incomeSourceDTO, token);
    }

    @GetMapping()
    public List<IncomeSource> getSourceByUser(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        return incomeSourceService.getSourceByUser(token);
    }
}


