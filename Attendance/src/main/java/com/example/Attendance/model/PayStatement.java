package com.example.Attendance.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "pay_statement")
@Getter

public class PayStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ps_id")
    private Integer id;

    @Column(name = "url", length = 150, nullable = false)
    private String url;

    @Column(name = "issuance_date", nullable = false)
    private LocalDate issuanceDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salary_id")
    private Salary salary;
}