package com.example.Attendance.controller;


import com.example.Attendance.dto.batch.pdf.PdfInputData;
import com.example.Attendance.service.batch.GCPService;
import com.example.Attendance.service.batch.PayStatementPdfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private final PayStatementPdfService payStatementPdfService;
    private final GCPService gcpService;

    @GetMapping("/test")
    public void test(){
        log.info("살려줘");
    }

    @GetMapping("/realtest")
    public void test2(){
        PdfInputData testData = new PdfInputData(
                123,                    // batchId
                456,                    // seId
                1,                      // status
                LocalDate.of(2024, 12, 2),  // issuanceDate
                "정상 발급",              // message
                "김철수",                // name
                "kim123@gmail.com",     // email
                LocalDate.of(1990, 5, 15), // birthDate
                "01012345678",          // phoneNumber
                3500000L,               // salary
                200000L,                // allowance
                180000L,                // nationalCharge
                150000L,                // insuranceCharge
                30000L,                 // employmentCharge
                120000L                 // incomeTax
        );

        byte[] pdf = payStatementPdfService.generateIncomeStatementPdf(testData);
        String url = gcpService.uploadObject(pdf);
    }
}
