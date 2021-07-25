package com.coffeeforest.domains.work_applicant.presentation;

import com.coffeeforest.domains.work_applicant.application.WorkApplicantFindService;
import com.coffeeforest.domains.work_applicant.application.dto.WorkApplicantFindRequest;
import com.coffeeforest.domains.work_applicant.application.dto.WorkApplicantInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkApplicantFindController {

  private final WorkApplicantFindService workApplicantFindService;

  @PostMapping("/work-applicant/list")
  public ResponseEntity<List<WorkApplicantInfo>> findAll(
      @RequestBody WorkApplicantFindRequest workApplicantFindRequest) {
    List<WorkApplicantInfo> workApplicantInfoList =
        workApplicantFindService.findAll(workApplicantFindRequest);
    return ResponseEntity.ok(workApplicantInfoList);
  }
}
