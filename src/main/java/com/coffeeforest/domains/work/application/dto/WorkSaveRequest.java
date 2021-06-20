package com.coffeeforest.domains.work.application.dto;

import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkSaveRequest {
  private CompanyEntity companyEntity;
  private UserEntity userEntity;
}
