package com.coffeeforest.domains.company.application;

import com.coffeeforest.domains.admin.application.AdminVerificationService;
import com.coffeeforest.domains.company.application.dto.CompanyConfigInfo;
import com.coffeeforest.domains.company.domain.CompanyConfigEntity;
import com.coffeeforest.domains.work.domain.WorkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyConfigUpdateService {

  private final AdminVerificationService adminVerificationService;

  @Transactional
  public CompanyConfigInfo update(CompanyConfigInfo companyConfigInfo) {
    WorkEntity workEntity =
        adminVerificationService.verify(
            companyConfigInfo.getUserIndex(), companyConfigInfo.getCompanyIndex());
    CompanyConfigEntity companyConfigEntity =
        workEntity.getCompanyEntity().getCompanyConfigEntity();

    companyConfigEntity.update(
        companyConfigInfo.getWorkStartTime(),
        companyConfigInfo.getWorkEndTime(),
        companyConfigInfo.getLunchStartTime(),
        companyConfigInfo.getLunchEndTime(),
        companyConfigInfo.isFlexibleState(),
        companyConfigInfo.getFlexibleWorkStartTime(),
        companyConfigInfo.getFlexibleWorkEndTime(),
        companyConfigInfo.getFullDayOffUnderYear(),
        companyConfigInfo.getFullDayOffUpperYear(),
        companyConfigInfo.getMorningDayOffStartTime(),
        companyConfigInfo.getMorningDayOffEndTime(),
        companyConfigInfo.getAfternoonDayOffStartTime(),
        companyConfigInfo.getAfternoonDayOffEndTime());

    return CompanyConfigInfo.builder()
        .companyIndex(workEntity.getCompanyEntity().getId())
        .userIndex(workEntity.getUserEntity().getId())
        .workStartTime(companyConfigEntity.getWorkStartTime())
        .workEndTime(companyConfigEntity.getWorkEndTime())
        .lunchStartTime(companyConfigEntity.getLunchStartTime())
        .lunchEndTime(companyConfigEntity.getLunchEndTime())
        .flexibleState(companyConfigEntity.isFlexibleState())
        .flexibleWorkStartTime(companyConfigEntity.getFlexibleWorkStartTime())
        .flexibleWorkEndTime(companyConfigEntity.getFlexibleWorkEndTime())
        .fullDayOffUnderYear(companyConfigEntity.getFullDayOffUnderYear())
        .fullDayOffUpperYear(companyConfigEntity.getFullDayOffUpperYear())
        .morningDayOffStartTime(companyConfigEntity.getMorningDayOffStartTime())
        .morningDayOffEndTime(companyConfigEntity.getMorningDayOffEndTime())
        .afternoonDayOffStartTime(companyConfigEntity.getAfternoonDayOffStartTime())
        .afternoonDayOffEndTime(companyConfigEntity.getAfternoonDayOffEndTime())
        .build();
  }
}
