package com.coffeeforest.domains.company_applicant.presentation;

import com.coffeeforest.domains.company_applicant.application.CompanyApplicantFindService;
import com.coffeeforest.domains.company_applicant.application.dto.CompanyApplicantFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyApplicantFindController {

  private final CompanyApplicantFindService companyApplicantFindService;

  @GetMapping("/company-applicant/{companyApplicantIndex}")
  public ResponseEntity<CompanyApplicantFindResponse> find(
      @PathVariable Long companyApplicantIndex) {
    CompanyApplicantFindResponse response = companyApplicantFindService.find(companyApplicantIndex);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/company-applicant")
  public ResponseEntity<List<CompanyApplicantFindResponse>> findAll() {
    List<CompanyApplicantFindResponse> responseList = companyApplicantFindService.findAll();
    return ResponseEntity.ok(responseList);
  }
}
