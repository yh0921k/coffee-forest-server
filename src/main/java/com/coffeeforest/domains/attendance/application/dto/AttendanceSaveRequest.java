package com.coffeeforest.domains.attendance.application.dto;

import lombok.Getter;

@Getter
public class AttendanceSaveRequest {
    private Long companyIndex;
    private Long userIndex;
}
