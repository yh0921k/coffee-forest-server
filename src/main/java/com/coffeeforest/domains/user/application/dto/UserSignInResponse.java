package com.coffeeforest.domains.user.application.dto;

import com.coffeeforest.domains.schedule.application.dto.ScheduleInfo;
import com.coffeeforest.domains.user.domain.Position;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSignInResponse {
    private Long companyIndex;
    private Long userIndex;
    private int year;
    private int month;
    private int weekNumber;
    private int weekStartDate;
    private int weekEndDate;
    private String name;
    private Position position;
    private Map<LocalDate, List<ScheduleInfo>> scheduleList;

}
