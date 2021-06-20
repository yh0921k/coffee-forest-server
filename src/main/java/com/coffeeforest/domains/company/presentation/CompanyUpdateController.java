package com.coffeeforest.domains.company.presentation;

import com.coffeeforest.domains.company.application.CompanyUpdateService;
import com.coffeeforest.domains.company.application.dto.CompanyUpdateRequest;
import com.coffeeforest.domains.company.application.dto.CompanyUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyUpdateController {
  private final CompanyUpdateService companyUpdateService;

  @PatchMapping("/company")
  public ResponseEntity<CompanyUpdateResponse> setBaseAttendanceTime(
      @RequestBody CompanyUpdateRequest companyUpdateRequest) {
    return ResponseEntity.ok(companyUpdateService.setBaseAttendanceTime(companyUpdateRequest));
  }
}
