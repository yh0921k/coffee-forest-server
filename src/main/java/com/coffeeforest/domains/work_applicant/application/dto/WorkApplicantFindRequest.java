package com.coffeeforest.domains.work_applicant.application.dto;

import lombok.Getter;

@Getter
public class WorkApplicantFindRequest {
  private Long ownerIndex;
  private Long companyIndex;
}
