package com.coffeeforest.domains.company.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanySaveRequest {
  private Long userIndex;
  private String name;
  private String address;
  private String businessNumber;
}
