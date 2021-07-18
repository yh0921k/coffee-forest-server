package com.coffeeforest.domains.work_applicant.application;

import com.coffeeforest.domains.company.application.CompanyFindService;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.work_applicant.application.dto.WorkApplicantSaveRequest;
import com.coffeeforest.domains.work_applicant.application.dto.WorkApplicantSaveResponse;
import com.coffeeforest.domains.work_applicant.domain.WorkApplicantEntity;
import com.coffeeforest.domains.work_applicant.domain.WorkApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class WorkApplicantSaveService {

  private final WorkApplicantRepository workApplicantRepository;
  private final CompanyFindService companyFindService;
  private final UserFindService userFindService;

  @Transactional
  public WorkApplicantSaveResponse save(WorkApplicantSaveRequest workApplicantSaveRequest) {
    CompanyEntity companyEntity =
        companyFindService.findById(workApplicantSaveRequest.getCompanyIndex());
    UserEntity userEntity = userFindService.findById(workApplicantSaveRequest.getUserIndex());

    WorkApplicantEntity workApplicantEntity =
        WorkApplicantEntity.builder().companyEntity(companyEntity).userEntity(userEntity).build();

    workApplicantRepository.save(workApplicantEntity);
    return WorkApplicantSaveResponse.builder()
        .companyIndex(workApplicantEntity.getCompanyEntity().getId())
        .userIndex(workApplicantEntity.getUserEntity().getId())
        .workStatus(workApplicantEntity.getWorkStatus())
        .build();
  }
}
