package com.coffeeforest.domains.company_config.application.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyConfigInfo {
  private Long userIndex;
  private Long companyIndex;

  private LocalTime workStartTime;
  private LocalTime workEndTime;

  private LocalTime lunchStartTime;
  private LocalTime lunchEndTime;

  private boolean flexibleState;
  private LocalTime flexibleWorkStartTime;
  private LocalTime flexibleWorkEndTime;

  private int fullDayOffUpperYear;
  private int fullDayOffUnderYear;

  private LocalTime morningDayOffStartTime;
  private LocalTime morningDayOffEndTime;

  private LocalTime afternoonDayOffStartTime;
  private LocalTime afternoonDayOffEndTime;
}
