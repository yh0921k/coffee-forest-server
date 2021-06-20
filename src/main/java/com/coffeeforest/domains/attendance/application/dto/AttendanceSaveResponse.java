package com.coffeeforest.domains.attendance.application.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AttendanceSaveResponse {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
