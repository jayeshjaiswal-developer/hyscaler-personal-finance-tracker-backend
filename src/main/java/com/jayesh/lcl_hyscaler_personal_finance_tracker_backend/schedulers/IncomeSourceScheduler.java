package com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.schedulers;

import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.enums.TransactionType;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.IncomeSource;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.modal.Transaction;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.IncomeSourceRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.repository.TransactionRepository;
import com.jayesh.lcl_hyscaler_personal_finance_tracker_backend.util.IncomeSourceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IncomeSourceScheduler {

    private final IncomeSourceRepository incomeSourceRepository;
    private final TransactionRepository transactionRepository;

    //    @Scheduled(fixedRate = 1000)
    @Scheduled(cron = "0 0 1 * * ?") // runs every day at 1 AM
    public void generateRecurringIncome(){
        System.out.println("Cron job getting executed");
        List<IncomeSource> incomeSources = incomeSourceRepository.findAll();
        for(IncomeSource incomeSource : incomeSources){
            LocalDate today = LocalDate.now();
            if(incomeSource.getNextExecutionDate().equals(today)){
//            if(true){
                Transaction transaction = new Transaction();
                transaction.setTransactionAmount(incomeSource.getSourceAmount());
                transaction.setTransactionDescription("Recurring Income: "+incomeSource.getSourceTitle());
                transaction.setTransactionDate(LocalDate.now());
                transaction.setTransactionType(TransactionType.INCOME);
                transaction.setIncomeSourceId(incomeSource);
                transaction.setUser(incomeSource.getUser());
                transactionRepository.save(transaction);
                incomeSource.setNextExecutionDate(IncomeSourceUtil.calculateNextDate(today, incomeSource.getSourceFrequency()));
                incomeSourceRepository.save(incomeSource);
            }
        }
    }
}
