package com.coffeeforest.domains.company.presentation;

import com.coffeeforest.domains.company.application.CompanyConfirmService;
import com.coffeeforest.domains.company.application.dto.CompanyConfirmRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyConfirmController {

  private final CompanyConfirmService companyConfirmService;

  @PostMapping("/company/accept")
  public ResponseEntity accept(@RequestBody CompanyConfirmRequest companyConfirmRequest) {
    companyConfirmService.accept(companyConfirmRequest);
    return ResponseEntity.ok().build();
  }
}
