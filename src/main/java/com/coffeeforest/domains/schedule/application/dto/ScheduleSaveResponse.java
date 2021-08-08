package com.coffeeforest.domains.schedule.application.dto;

import com.coffeeforest.domains.schedule.domain.ScheduleType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ScheduleSaveResponse {
    private Long userIndex;
    private Long scheduleIndex;
    private String userName;
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private ScheduleType scheduleType;
}
