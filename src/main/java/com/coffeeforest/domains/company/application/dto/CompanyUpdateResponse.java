package com.coffeeforest.domains.company.application.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyUpdateResponse {
  private LocalTime baseStartTime;
  private LocalTime baseEndTime;
}
