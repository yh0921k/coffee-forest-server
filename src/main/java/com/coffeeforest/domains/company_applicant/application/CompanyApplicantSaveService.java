package com.coffeeforest.domains.company_applicant.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.commons.regex.RegExType;
import com.coffeeforest.commons.regex.RegExUtils;
import com.coffeeforest.domains.company_applicant.application.dto.CompanyApplicantInfo;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantEntity;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantRepository;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyApplicantSaveService {

  private final CompanyApplicantRepository companyApplicantRepository;
  private final UserFindService userFindService;
  private final RegExUtils regExUtils;

  @Transactional
  public CompanyApplicantInfo save(CompanyApplicantInfo companyApplicantInfo) {
    UserEntity userEntity = userFindService.findById(companyApplicantInfo.getUserIndex());

    if (!regExUtils.validate(RegExType.BUSINESS_NUMBER, companyApplicantInfo.getBusinessNumber())) {
      throw new InvalidArgumentException(
          ExceptionState.INVALID_ARGUMENT, "Invalid Business Number Format");
    }

    CompanyApplicantEntity companyApplicantEntity =
        CompanyApplicantEntity.builder()
            .name(companyApplicantInfo.getName())
            .address(companyApplicantInfo.getAddress())
            .businessNumber(companyApplicantInfo.getBusinessNumber())
            .owner(userEntity)
            .build();

    companyApplicantRepository.save(companyApplicantEntity);
    return CompanyApplicantInfo.builder()
        .userIndex(userEntity.getId())
        .name(companyApplicantEntity.getName())
        .address(companyApplicantEntity.getAddress())
        .businessNumber(companyApplicantEntity.getBusinessNumber())
        .companyApplicantStatus(companyApplicantEntity.getCompanyApplicantStatus())
        .build();
  }
}
