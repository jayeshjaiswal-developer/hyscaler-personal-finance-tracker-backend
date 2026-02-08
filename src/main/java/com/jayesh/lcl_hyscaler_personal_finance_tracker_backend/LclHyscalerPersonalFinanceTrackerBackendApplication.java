package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LclHyscalerPersonalFinanceTrackerBackendApplication {

	public static void main(String[] args) {
        try{
            SpringApplication.run(LclHyscalerPersonalFinanceTrackerBackendApplication.class, args);
        }catch(Exception e){
            System.out.println("Exception Catched");
        }
	}
}

