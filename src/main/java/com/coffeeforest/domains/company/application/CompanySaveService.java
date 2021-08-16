package com.coffeeforest.domains.company.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.commons.regex.RegExType;
import com.coffeeforest.commons.regex.RegExUtils;
import com.coffeeforest.domains.company.application.dto.CompanySaveRequest;
import com.coffeeforest.domains.company_config.application.CompanyConfigSaveService;
import com.coffeeforest.domains.company_config.domain.CompanyConfigEntity;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company.domain.CompanyRepository;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CompanySaveService {

  private final CompanyConfigSaveService companyConfigSaveService;
  private final CompanyRepository companyRepository;
  private final RegExUtils regExUtils;
  private final UserFindService userFindService;

  @Transactional
  public CompanyEntity save(CompanySaveRequest companySaveRequest) {
    UserEntity userEntity = userFindService.findById(companySaveRequest.getUserIndex());

    CompanyConfigEntity companyConfigEntity = companyConfigSaveService.createAndSave();
    CompanyEntity companyEntity =
        CompanyEntity.builder()
            .name(companySaveRequest.getName())
            .address(companySaveRequest.getAddress())
            .businessNumber(companySaveRequest.getBusinessNumber())
            .owner(userEntity)
            .companyConfigEntity(companyConfigEntity)
            .build();
    return companyRepository.save(companyEntity);
  }

  public String isDuplicated(String businessNumber) {
    if (!regExUtils.validate(RegExType.BUSINESS_NUMBER, businessNumber)) {
      throw new InvalidArgumentException(
          ExceptionState.INVALID_ARGUMENT, "Invalid Business Number Format");
    }

    String result =
        companyRepository.existsByBusinessNumber(businessNumber) ? "Duplicated" : "Available";
    return result;
  }
}
