package com.example.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "Attendance")
public interface TestwithEmployee {

    @GetMapping("/employeefeign")
    String feigntestemployeeceo();
}
