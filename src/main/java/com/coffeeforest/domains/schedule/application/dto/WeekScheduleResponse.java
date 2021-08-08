package com.coffeeforest.domains.schedule.application.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WeekScheduleResponse {
  private Long userIndex;
  private String userName;
  private String profileImage;
  Map<LocalDate, List<ScheduleInfo>> scheduleInfo;
}
