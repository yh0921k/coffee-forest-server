package com.coffeeforest.domains.schedule.application.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleFindRequest {
  private Long userIndex;
  private Long companyIndex;
  private LocalDate startDate;
}
