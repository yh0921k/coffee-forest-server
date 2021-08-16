package com.coffeeforest.domains.work.application.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkUpdateInfo {
  private Long adminIndex;
  private Long companyIndex;
  private Long userIndex;

  private boolean flexibleState;
  private LocalTime workStartTime;
  private LocalTime workEndTime;
  private int fullDayOffCount;
}
