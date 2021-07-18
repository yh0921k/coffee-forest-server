package com.coffeeforest.domains.work_applicant.application.dto;

import com.coffeeforest.domains.work.domain.WorkStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkApplicantSaveResponse {
  private Long companyIndex;
  private Long userIndex;
  private WorkStatus workStatus;
}
