package com.coffeeforest.domains.company_applicant.presentation;

import com.coffeeforest.domains.company_applicant.application.CompanyApplicantSaveService;
import com.coffeeforest.domains.company_applicant.application.dto.CompanyApplicantInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyApplicantSaveController {

  private final CompanyApplicantSaveService companyApplicantSaveService;

  @PostMapping("/company-applicant")
  public ResponseEntity<CompanyApplicantInfo> save(
      @RequestBody CompanyApplicantInfo companyApplicantInfo) {
    return ResponseEntity.ok(companyApplicantSaveService.save(companyApplicantInfo));
  }
}
