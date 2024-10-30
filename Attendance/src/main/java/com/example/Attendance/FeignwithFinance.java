package com.example.Attendance;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "Finance")
public interface FeignwithFinance {

    @GetMapping("/financefeign")
    String feigntestemployeefinance();
}
