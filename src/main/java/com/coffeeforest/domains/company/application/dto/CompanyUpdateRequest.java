package com.coffeeforest.domains.company.application.dto;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class CompanyUpdateRequest {
  private Long companyIndex;
  private LocalTime baseStartTime;
  private LocalTime baseEndTime;
}
