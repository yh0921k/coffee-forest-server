package com.coffeeforest.domains.company.application;

import com.coffeeforest.domains.company.application.dto.CompanySaveRequest;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company.domain.CompanyRepository;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CompanySaveService {

  private final CompanyRepository companyRepository;

  @Transactional
  public CompanyEntity save(CompanySaveRequest companySaveRequest, UserEntity userEntity) {
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
    String result =
        companyRepository.existsByBusinessNumber(businessNumber) ? "Duplicated" : "Available";
    return result;
  }
}
