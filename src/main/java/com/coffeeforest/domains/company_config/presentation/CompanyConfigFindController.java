package com.coffeeforest.domains.company_config.presentation;

import com.coffeeforest.domains.company_config.application.CompanyConfigFindService;
import com.coffeeforest.domains.company_config.application.dto.CompanyConfigInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyConfigFindController {

  private final CompanyConfigFindService companyConfigFindService;

  @GetMapping("/company-config/{companyIndex}")
  public ResponseEntity<CompanyConfigInfo> findByIndex(@PathVariable Long companyIndex) {
    CompanyConfigInfo response = companyConfigFindService.findByIndex(companyIndex);
    return ResponseEntity.ok(response);
  }
}
