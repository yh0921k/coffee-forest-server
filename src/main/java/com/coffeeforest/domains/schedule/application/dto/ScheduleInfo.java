package com.coffeeforest.domains.schedule.application.dto;

import com.coffeeforest.domains.schedule.domain.ScheduleType;
import lombok.*;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ScheduleInfo {
  private Long scheduleIndex;
  private LocalTime startTime;
  private LocalTime endTime;
  private ScheduleType scheduleType;
}
