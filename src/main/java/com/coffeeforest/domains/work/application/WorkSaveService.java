package com.coffeeforest.domains.work.application;

import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.user.domain.Position;
import com.coffeeforest.domains.work.application.dto.WorkSaveRequest;
import com.coffeeforest.domains.work.domain.WorkEntity;
import com.coffeeforest.domains.work.domain.WorkRepository;
import com.coffeeforest.domains.work.domain.WorkStatus;
import com.coffeeforest.domains.work_applicant.application.WorkApplicantDeleteService;
import com.coffeeforest.domains.work_applicant.application.WorkApplicantFindService;
import com.coffeeforest.domains.work_applicant.domain.WorkApplicantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WorkSaveService {

  private final WorkRepository workRepository;
  private final WorkFindService workFindService;
  private final WorkApplicantFindService workApplicantFindService;
  private final WorkApplicantDeleteService workApplicantDeleteService;

  @Transactional
  public WorkEntity ofApplicant(WorkApplicantEntity workApplicantEntity) {
    WorkEntity workEntity =
        WorkEntity.builder()
            .companyEntity(workApplicantEntity.getCompanyEntity())
            .userEntity(workApplicantEntity.getUserEntity())
            .startDate(LocalDate.now())
            .endDate(null)
            .workStatus(WorkStatus.WORKING)
            .build();

    return workRepository.save(workEntity);
  }

  public void accept(WorkSaveRequest workSaveRequest) {
    WorkEntity workEntity =
        workFindService.findByUserIndexAndCompanyIndex(
            workSaveRequest.getAcceptorIndex(), workSaveRequest.getCompanyIndex());
    workEntity.getUserEntity().isValidPosition(Position.ADMIN);

    Long workApplicantIndex = workSaveRequest.getWorkApplicantIndex();
    WorkApplicantEntity workApplicantEntity =
        workApplicantFindService.findByIndex(workApplicantIndex);
    ofApplicant(workApplicantEntity);

    workApplicantDeleteService.deleteByIndex(workApplicantIndex);
  }

  @Transactional
  public void ofCompany(CompanyEntity companyEntity) {
    workRepository.save(
        WorkEntity.builder()
            .companyEntity(companyEntity)
            .userEntity(companyEntity.getOwner())
            .startDate(LocalDate.now())
            .endDate(null)
            .workStatus(WorkStatus.WORKING)
            .build());
  }
}
