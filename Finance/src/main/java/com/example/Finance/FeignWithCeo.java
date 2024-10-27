package com.example.Finance;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "Ceo")
public interface FeignWithCeo {

    @GetMapping("/ceofeign")
    public String requesttoCEO();
}
