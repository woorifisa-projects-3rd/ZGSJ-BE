package com.example.CEO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "Employee")
public interface TestwithEmployee {

    @GetMapping("/employeefeign")
    String feigntestemployeeceo();
}
