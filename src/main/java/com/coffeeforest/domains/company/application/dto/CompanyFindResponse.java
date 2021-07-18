package com.coffeeforest.domains.company.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyFindResponse {
  private Long companyIndex;
  private String companyName;
  private String companyAddress;

  private Long ownerIndex;
  private String ownerName;
  private String ownerProfile;
}
