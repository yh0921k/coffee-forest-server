package com.coffeeforest.domains.company.application;

import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company.domain.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
