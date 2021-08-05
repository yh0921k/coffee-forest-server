package com.coffeeforest.domains.company.application;

import com.coffeeforest.domains.company.application.dto.CompanyConfirmRequest;
import com.coffeeforest.domains.company.application.dto.CompanySaveRequest;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company_applicant.application.CompanyApplicantFindService;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantEntity;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantStatus;
import com.coffeeforest.domains.user.domain.Position;
import com.coffeeforest.domains.work.application.WorkSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyConfirmService {

  private final CompanyApplicantFindService companyApplicantFindService;
  private final CompanySaveService companySaveService;
  private final WorkSaveService workSaveService;

  @Transactional
  public void accept(CompanyConfirmRequest companyConfirmRequest) {
    CompanyApplicantEntity companyApplicantEntity =
        companyApplicantFindService.findByIndex(companyConfirmRequest.getCompanyApplicantIndex());

    CompanyEntity companyEntity =
        companySaveService.save(
            CompanySaveRequest.builder()
                .userIndex(companyConfirmRequest.getUserIndex())
                .name(companyApplicantEntity.getName())
                .address(companyApplicantEntity.getAddress())
                .businessNumber(companyApplicantEntity.getBusinessNumber())
                .build());

    companyEntity.getOwner().updatePosition(Position.ADMIN);
    workSaveService.ofCompany(companyEntity);
    companyApplicantEntity.status(CompanyApplicantStatus.ACCEPTED);
  }
}
