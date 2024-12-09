package com.example.Attendance.dto;

import com.example.Attendance.model.Commute;
import com.example.Attendance.model.StoreEmployee;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommuteByPresidentRequest {

    @NotNull(message = "출근 시간은 필수입니다")
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @NotNull(message = "출근일자는 필수입니다")
    @PastOrPresent(message = "미래 날짜는 입력할 수 없습니다")
    private ZonedDateTime commuteDate;

//    private CommuteByPresidentRequest(LocalDateTime startTime, LocalDateTime endTime, LocalDate commuteDate) {
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.commuteDate = commuteDate;
//    }


    public Commute toEntity(StoreEmployee employee) {
        return endTime == null ?
                Commute.createCommuteCheckIn(commuteDate.toLocalDate(), startTime, employee) :
                Commute.createCompleteCommute(commuteDate.toLocalDate(), startTime, endTime, employee);
    }
}
