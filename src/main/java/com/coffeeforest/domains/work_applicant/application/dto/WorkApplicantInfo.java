package com.coffeeforest.domains.work_applicant.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkApplicantInfo {
  private Long workApplicantIndex;
  private String name;
  private String email;
  private String profileImage;
}
