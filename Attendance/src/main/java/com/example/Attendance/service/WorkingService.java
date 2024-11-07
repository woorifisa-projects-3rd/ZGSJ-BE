package com.example.Attendance.service;

import com.example.Attendance.dto.WorkingResponse;
import com.example.Attendance.repository.WorkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkingService {
    private final WorkingRepository workingRepository;

//    public List<WorkingResponse> getMonthlyEmployeeWorking(int year, int month, int presidentId)
//    {
//        workingRepository.
//    }
}
