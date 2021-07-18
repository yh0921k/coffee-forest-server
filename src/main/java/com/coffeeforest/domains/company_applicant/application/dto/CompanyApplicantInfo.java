package com.coffeeforest.domains.company_applicant.application.dto;

import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyApplicantInfo {
  private Long userIndex;
  private String name;
  private String address;
  private String businessNumber;
  private CompanyApplicantStatus companyApplicantStatus;
}
