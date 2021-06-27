package com.coffeeforest.domains.schedule.application.dto;

import com.coffeeforest.domains.schedule.domain.ScheduleStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ScheduleSaveRequest {
    private Long userIndex;
    private Long companyIndex;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private ScheduleStatus scheduleStatus;
}
