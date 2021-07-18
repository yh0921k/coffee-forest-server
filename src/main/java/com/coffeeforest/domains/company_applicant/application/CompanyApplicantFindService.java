package com.coffeeforest.domains.company_applicant.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.domains.company_applicant.application.dto.CompanyApplicantFindResponse;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantEntity;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantRepository;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantStatus;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyApplicantFindService {

  private final CompanyApplicantRepository companyApplicantRepository;

  @Transactional
  public CompanyApplicantFindResponse find(Long companyApplicantIndex) {
    CompanyApplicantEntity companyApplicantEntity = findByIndex(companyApplicantIndex);
    UserEntity userEntity = companyApplicantEntity.getOwner();

    return CompanyApplicantFindResponse.builder()
        .index(companyApplicantEntity.getId())
        .companyName(companyApplicantEntity.getName())
        .address(companyApplicantEntity.getAddress())
        .businessNumber(companyApplicantEntity.getBusinessNumber())
        .userIndex(userEntity.getId())
        .userName(userEntity.getName())
        .email(userEntity.getEmail())
        .phone(userEntity.getPhone())
        .build();
  }

  @Transactional
  public CompanyApplicantEntity findByIndex(Long companyApplicantIndex) {
    CompanyApplicantEntity companyApplicantEntity =
        companyApplicantRepository
            .findById(companyApplicantIndex)
            .orElseThrow(
                () ->
                    new InvalidArgumentException(
                        ExceptionState.INVALID_ARGUMENT, "Invalid Applicant Index"));

    return companyApplicantEntity;
  }

  @Transactional
  public List<CompanyApplicantFindResponse> findAll() {
    List<CompanyApplicantEntity> entityList =
        companyApplicantRepository
            .findAllByCompanyApplicantStatus(CompanyApplicantStatus.WAIT)
            .orElse(Collections.emptyList());

    List<CompanyApplicantFindResponse> responseList =
        entityList.stream()
            .map(
                entity -> {
                  UserEntity userEntity = entity.getOwner();

                  return CompanyApplicantFindResponse.builder()
                      .index(entity.getId())
                      .companyName(entity.getName())
                      .address(entity.getAddress())
                      .businessNumber(entity.getBusinessNumber())
                      .userIndex(userEntity.getId())
                      .userName(userEntity.getName())
                      .email(userEntity.getEmail())
                      .phone(userEntity.getPhone())
                      .build();
                })
            .collect(Collectors.toList());

    return responseList;
  }
}
