package com.coffeeforest.domains.admin.application;

import com.coffeeforest.domains.admin.application.dto.CompanyUserIndexPairRequest;
import com.coffeeforest.domains.user.domain.Position;
import com.coffeeforest.domains.work.application.WorkFindService;
import com.coffeeforest.domains.work.domain.WorkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminVerificationService {

  private final WorkFindService workFindService;

  public WorkEntity verify(CompanyUserIndexPairRequest companyUserIndexPairRequest) {
    return this.verify(companyUserIndexPairRequest.getUserIndex(), companyUserIndexPairRequest.getCompanyIndex());
  }

  public WorkEntity verify(Long userIndex, Long companyIndex) {
    WorkEntity workEntity = workFindService.findByUserIndexAndCompanyIndex(userIndex, companyIndex);
    workEntity.getUserEntity().isValidPosition(Position.ADMIN);

    return workEntity;
  }
}
