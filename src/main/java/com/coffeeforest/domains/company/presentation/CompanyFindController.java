package com.coffeeforest.domains.company.presentation;

import com.coffeeforest.domains.company.application.CompanyFindService;
import com.coffeeforest.domains.company.application.dto.CompanyFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyFindController {

  private final CompanyFindService companyFindService;

  @GetMapping("/company")
  public ResponseEntity<List<CompanyFindResponse>> findAll() {
    List<CompanyFindResponse> responseList = companyFindService.findAll();
    return ResponseEntity.ok(responseList);
  }
}
