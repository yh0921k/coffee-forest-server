package com.coffeeforest.domains.company.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.commons.regex.RegExType;
import com.coffeeforest.commons.regex.RegExUtils;
import com.coffeeforest.domains.company.application.dto.CompanySaveRequest;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company.domain.CompanyRepository;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CompanySaveService {

  private final CompanyRepository companyRepository;
  private final RegExUtils regExUtils;
  private final UserFindService userFindService;

  @Transactional
  public CompanyEntity save(CompanySaveRequest companySaveRequest) {
    UserEntity userEntity = userFindService.findById(companySaveRequest.getUserIndex());
    CompanyEntity companyEntity =
        CompanyEntity.builder()
            .name(companySaveRequest.getName())
            .address(companySaveRequest.getAddress())
            .businessNumber(companySaveRequest.getBusinessNumber())
            .owner(userEntity)
            .build();
    companyEntity.baseAttendanceTime(LocalTime.of(9, 0), LocalTime.of(18, 0));
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
