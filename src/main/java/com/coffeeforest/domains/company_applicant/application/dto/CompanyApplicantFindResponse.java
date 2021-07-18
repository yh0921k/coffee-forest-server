package com.coffeeforest.domains.company_applicant.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyApplicantFindResponse {
  private Long index;
  private String companyName;
  private String address;
  private String businessNumber;

  private Long userIndex;
  private String email;
  private String userName;
  private String phone;
}
