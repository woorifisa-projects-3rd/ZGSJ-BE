package com.example.core_bank.core_bank.service;

import com.example.core_bank.core_bank.dto.businessnumber.BusinessNumberResponse;
import com.example.core_bank.core_bank.exception.BusinessNumberException;
import com.example.core_bank.core_bank.repository.BusinessNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessNumberService {
    private final BusinessNumberRepository businessNumberRepository;

    public BusinessNumberResponse existsByBusinessNumber(String businessNumber) {
        try {
            boolean exists = businessNumberRepository.existsByBusinessNumber(businessNumber);
            return new BusinessNumberResponse(exists, false,
                    exists ? "등록된 사업자번호입니다." : "미등록된 사업자번호입니다.");
        } catch (Exception e) {
            throw new BusinessNumberException();
        }
    }

}
