package com.coffeeforest.domains.work.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkSaveRequest {
  private Long acceptorIndex;
  private Long companyIndex;
  private Long workApplicantIndex;
}
