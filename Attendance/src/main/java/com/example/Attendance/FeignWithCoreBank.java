package com.example.Attendance;

import com.example.Attendance.dto.TransferRequest;
import com.example.Attendance.dto.TransferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "core",url= "http://3.39.182.226:3456")
public interface FeignWithCoreBank {

    @PostMapping("/automaticTransfer")
    TransferResponse automaticTransfer(@RequestBody TransferRequest transferRequest);
}
