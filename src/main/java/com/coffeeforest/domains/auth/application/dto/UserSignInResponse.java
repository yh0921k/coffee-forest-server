package com.coffeeforest.domains.auth.application.dto;

import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantStatus;
import com.coffeeforest.domains.user.domain.Position;
import com.coffeeforest.domains.work.domain.WorkStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSignInResponse {
  private Long userIndex;
  private String email;
  private String name;
  private Position position;
  private String profileImage;
  private UserTokenInfo userTokenInfo;

  private Long companyIndex;
  private WorkStatus workApplicantStatus;
  private CompanyApplicantStatus companyApplicantStatus;
}
