package com.coffeeforest.domains.company_config.application;

import com.coffeeforest.domains.company.application.CompanyFindService;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company_config.application.dto.CompanyConfigInfo;
import com.coffeeforest.domains.company_config.domain.CompanyConfigEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyConfigFindService {

  private final CompanyFindService companyFindService;

  public CompanyConfigInfo findByIndex(Long companyIndex) {
    CompanyEntity companyEntity = companyFindService.findById(companyIndex);
    CompanyConfigEntity companyConfigEntity = companyEntity.getCompanyConfigEntity();

    return CompanyConfigInfo.builder()
        .companyIndex(companyEntity.getId())
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
