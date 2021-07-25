package com.coffeeforest.domains.work_applicant.application;

import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.work_applicant.application.dto.WorkApplicantFindRequest;
import com.coffeeforest.domains.work_applicant.application.dto.WorkApplicantInfo;
import com.coffeeforest.domains.work_applicant.domain.WorkApplicantEntity;
import com.coffeeforest.domains.work_applicant.domain.WorkApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkApplicantFindService {

  private final WorkApplicantRepository workApplicantRepository;

  @Transactional
  public List<WorkApplicantInfo> findAll(WorkApplicantFindRequest workApplicantFindRequest) {
    List<WorkApplicantEntity> workApplicantEntityList =
        workApplicantRepository.findAllByCompanyEntityId(
            workApplicantFindRequest.getCompanyIndex());

    List<WorkApplicantInfo> workApplicantInfoList =
        workApplicantEntityList.stream()
            .map(
                workApplicantEntity -> {
                  UserEntity userEntity = workApplicantEntity.getUserEntity();

                  return WorkApplicantInfo.builder()
                      .workApplicantIndex(workApplicantEntity.getId())
                      .name(userEntity.getName())
                      .email(userEntity.getEmail())
                      .profileImage(userEntity.getProfileImage())
                      .build();
                })
            .collect(Collectors.toList());

    return workApplicantInfoList;
  }
}
