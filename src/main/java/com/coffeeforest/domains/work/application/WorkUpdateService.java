package com.coffeeforest.domains.work.application;

import com.coffeeforest.domains.admin.application.AdminVerificationService;
import com.coffeeforest.domains.work.application.dto.WorkUpdateInfo;
import com.coffeeforest.domains.work.domain.WorkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkUpdateService {

  private final AdminVerificationService adminVerificationService;
  private final WorkFindService workFindService;

  @Transactional
  public WorkUpdateInfo update(WorkUpdateInfo workUpdateInfo) {
    adminVerificationService.verify(
        workUpdateInfo.getAdminIndex(), workUpdateInfo.getCompanyIndex());

    WorkEntity workEntity =
        workFindService.findByUserIndexAndCompanyIndex(
            workUpdateInfo.getUserIndex(), workUpdateInfo.getCompanyIndex());
    workEntity.flexibleInfo(
        workUpdateInfo.isFlexibleState(),
        workUpdateInfo.getWorkStartTime(),
        workUpdateInfo.getWorkEndTime(),
        workUpdateInfo.getFullDayOffCount());

    return WorkUpdateInfo.builder()
        .userIndex(workEntity.getUserEntity().getId())
        .flexibleState(workEntity.isFlexibleState())
        .workStartTime(workEntity.getWorkStartTime())
        .workEndTime(workEntity.getWorkEndTime())
        .fullDayOffCount(workEntity.getFullDayOffCount())
        .build();
  }
}
