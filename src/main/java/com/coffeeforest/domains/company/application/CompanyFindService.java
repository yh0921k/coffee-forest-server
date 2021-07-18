package com.coffeeforest.domains.company.application;

import com.coffeeforest.domains.company.application.dto.CompanyFindResponse;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company.domain.CompanyRepository;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyFindService {

  private final CompanyRepository companyRepository;

  @Transactional
  public CompanyEntity findByName(String name) {
    CompanyEntity companyEntity =
        companyRepository
            .findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Company Name"));

    return companyEntity;
  }

  @Transactional
  public CompanyEntity findById(Long companyIndex) {
    CompanyEntity companyEntity =
        companyRepository
            .findById(companyIndex)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Company Index"));

    return companyEntity;
  }

  @Transactional
  public List<CompanyFindResponse> findAll() {
    List<CompanyEntity> entityList = companyRepository.findAll();

    List<CompanyFindResponse> responseList =
        entityList.stream()
            .map(
                companyEntity -> {
                  UserEntity owner = companyEntity.getOwner();

                  return CompanyFindResponse.builder()
                      .companyIndex(companyEntity.getId())
                      .companyName(companyEntity.getName())
                      .companyAddress(companyEntity.getAddress())
                      .ownerIndex(owner.getId())
                      .ownerName(owner.getName())
                      .ownerProfile(owner.getProfileImage())
                      .build();
                })
            .collect(Collectors.toList());

    return responseList;
  }
}
