package com.coffeeforest.domains.company.application;

import com.coffeeforest.domains.company.application.dto.CompanyUpdateRequest;
import com.coffeeforest.domains.company.application.dto.CompanyUpdateResponse;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company.domain.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyUpdateService {
  private final CompanyFindService companyFindService;

  @Transactional
  public CompanyUpdateResponse setBaseAttendanceTime(CompanyUpdateRequest companyUpdateRequest) {
    CompanyEntity companyEntity =
        companyFindService.findById(companyUpdateRequest.getCompanyIndex());

    companyEntity.baseAttendanceTime(
        companyUpdateRequest.getBaseStartTime(), companyUpdateRequest.getBaseEndTime());

    return CompanyUpdateResponse.builder()
        .baseStartTime(companyEntity.getStartTime())
        .baseEndTime(companyEntity.getEndTime())
        .build();
  }
}
