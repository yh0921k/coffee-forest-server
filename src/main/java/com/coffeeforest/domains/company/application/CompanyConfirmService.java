package com.coffeeforest.domains.company.application;

import com.coffeeforest.domains.company.application.dto.CompanyConfirmRequest;
import com.coffeeforest.domains.company.application.dto.CompanySaveRequest;
import com.coffeeforest.domains.company_applicant.application.CompanyApplicantFindService;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantEntity;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyConfirmService {

  private final CompanyApplicantFindService companyApplicantFindService;
  private final CompanySaveService companySaveService;

  @Transactional
  public void accept(CompanyConfirmRequest companyConfirmRequest) {
    CompanyApplicantEntity companyApplicantEntity =
        companyApplicantFindService.findByIndex(companyConfirmRequest.getCompanyApplicantIndex());

    companySaveService.save(
        CompanySaveRequest.builder()
            .userIndex(companyConfirmRequest.getUserIndex())
            .name(companyApplicantEntity.getName())
            .address(companyApplicantEntity.getAddress())
            .businessNumber(companyApplicantEntity.getBusinessNumber())
            .build());

    companyApplicantEntity.status(CompanyApplicantStatus.ACCEPTED);
  }
}
