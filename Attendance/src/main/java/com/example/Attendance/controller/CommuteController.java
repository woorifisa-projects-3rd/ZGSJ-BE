package com.example.Attendance.controller;

import com.example.Attendance.dto.WorkingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/commute")
@RestController
@RequiredArgsConstructor
public class CommuteController {
    //사장님이 출퇴근 crud 용

    @GetMapping("employees")
    public ResponseEntity<List<WorkingResponse>> getMonthlyEmployeeWorking(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int presidentId)
    {

    }

}
