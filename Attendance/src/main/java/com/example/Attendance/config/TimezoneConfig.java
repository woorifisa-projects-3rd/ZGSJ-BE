package com.example.Attendance.config;

import java.util.TimeZone;

@Configuration
public class TimezoneConfig {
    @PostConstruct
    public void init() {
        // 전체 JVM에서 기본 시간대를 서울로 설정
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}