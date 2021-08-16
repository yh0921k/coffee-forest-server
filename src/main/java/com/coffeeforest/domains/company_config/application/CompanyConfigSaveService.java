package com.coffeeforest.domains.company_config.application;

import com.coffeeforest.domains.company_config.domain.CompanyConfigEntity;
import com.coffeeforest.domains.company_config.domain.CompanyConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyConfigSaveService {

  private final CompanyConfigRepository companyConfigRepository;

  public CompanyConfigEntity createAndSave() {
    return companyConfigRepository.save(CompanyConfigEntity.builder().build());
  }
}
