package com.coffeeforest.domains.company.presentation;

import com.coffeeforest.domains.company.application.CompanyConfigUpdateService;
import com.coffeeforest.domains.company.application.dto.CompanyConfigInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyConfigUpdateController {

  private final CompanyConfigUpdateService companyConfigUpdateService;

  @PutMapping("/company-config")
  public ResponseEntity<CompanyConfigInfo> update(@RequestBody CompanyConfigInfo companyConfigInfo) {
    CompanyConfigInfo response = companyConfigUpdateService.update(companyConfigInfo);
    return ResponseEntity.ok(companyConfigInfo);
  }
}
