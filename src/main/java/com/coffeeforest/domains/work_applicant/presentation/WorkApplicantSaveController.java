package com.coffeeforest.domains.work_applicant.presentation;

import com.coffeeforest.domains.work_applicant.application.WorkApplicantSaveService;
import com.coffeeforest.domains.work_applicant.application.dto.WorkApplicantSaveRequest;
import com.coffeeforest.domains.work_applicant.application.dto.WorkApplicantSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkApplicantSaveController {

  private final WorkApplicantSaveService workApplicantSaveService;

  @PostMapping("/work-applicant")
  public ResponseEntity<WorkApplicantSaveResponse> save(
      @RequestBody WorkApplicantSaveRequest workApplicantSaveRequest) {
    WorkApplicantSaveResponse response = workApplicantSaveService.save(workApplicantSaveRequest);
    return ResponseEntity.ok(response);
  }
}
