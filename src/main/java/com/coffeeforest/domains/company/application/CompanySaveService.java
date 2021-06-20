package com.coffeeforest.domains.company.application;

import com.coffeeforest.domains.company.application.dto.CompanySaveRequest;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company.domain.CompanyRepository;
import com.coffeeforest.domains.user.domain.UserEntity;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanySaveService {

  private final CompanyRepository companyRepository;

  @Transactional
  public CompanyEntity save(CompanySaveRequest companySaveRequest, UserEntity userEntity) {
    return companyRepository.save(
        CompanyEntity.builder()
            .name(companySaveRequest.getName())
            .address(companySaveRequest.getAddress())
            .businessNumber(companySaveRequest.getBusinessNumber())
            .owner(userEntity)
            .build());
  }

  public String isDuplicated(String businessNumber) {
    String result =
        companyRepository.existsByBusinessNumber(businessNumber) ? "Duplicated" : "Available";
    return result;
  }
}
