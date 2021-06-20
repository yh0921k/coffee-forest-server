package com.coffeeforest.domains.attendance.application.dto;

import lombok.Getter;

@Getter
public class AttendanceTimeRequest {
    private Long companyIndex;
    private Long userIndex;
}
