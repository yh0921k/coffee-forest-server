package com.coffeeforest.domains.company.application.dto;

import lombok.Getter;

@Getter
public class CompanyConfirmRequest {
  private Long userIndex;
  private Long companyApplicantIndex;
  private String rejectReason;
}
