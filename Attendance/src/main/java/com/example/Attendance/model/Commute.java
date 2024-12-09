package com.example.Attendance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "commute")
@Getter
@NoArgsConstructor
public class Commute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commute_id")
    private Integer id;

    @Column(name = "commute_date", nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate commuteDate;


    @Column(name = "commute_duration")
    private Long commuteDuration;

    @Column(name = "start_time", nullable = false, columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "se_id")
    private StoreEmployee storeEmployee;

    private Commute(LocalDate commuteDate, LocalDateTime startTime, StoreEmployee storeEmployee) {
        this.commuteDate = commuteDate;
        this.commuteDuration = 0L;
        this.startTime = startTime;
        this.storeEmployee = storeEmployee;
    }

    //사장님이 출퇴근 모두 지정 용
    private Commute(LocalDate commuteDate, LocalDateTime startTime, LocalDateTime endTime, StoreEmployee storeEmployee) {
        this.commuteDate = commuteDate;
        this.startTime = startTime;
        this.endTime = endTime;
        calculateDuration(startTime, endTime);
        this.storeEmployee = storeEmployee;
    }


    public static Commute createCommuteCheckIn(LocalDate commuteDate, LocalDateTime startTime, StoreEmployee storeEmployee) {
        return new Commute(commuteDate, startTime, storeEmployee);
    }

    public static Commute createCompleteCommute(LocalDate commuteDate, LocalDateTime startTime, LocalDateTime endTime, StoreEmployee storeEmployee) {
        return new Commute(commuteDate, startTime, endTime, storeEmployee);
    }

    private void calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        if (endTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            LocalDateTime adjustedEndTime = endTime.plusDays(1);
            this.commuteDuration = Duration.between(startTime, adjustedEndTime).toMinutes();
        } else if (endTime.isBefore(startTime)) {
            // 퇴근 시간이 출근 시간보다 이전인 경우 (자정을 넘긴 경우)
            // 이거 말고 다른 오전 값은 들어올 수 없음(FRONT 측 계산)
            LocalDateTime adjustedEndTime = endTime.plusDays(1);
            this.commuteDuration = Duration.between(startTime, adjustedEndTime).toMinutes();
        } else {
            // 일반적인 경우
            this.commuteDuration = Duration.between(startTime, endTime).toMinutes();
        }
    }

    public void setEndTime(LocalDateTime endTime) {
        calculateDuration(this.startTime, endTime);
        this.endTime = endTime;
    }
}