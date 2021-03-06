package com.coffeeforest.domains.schedule.presentation;

import com.coffeeforest.domains.admin.application.dto.CompanyUserIndexPairRequest;
import com.coffeeforest.domains.schedule.application.ScheduleAcceptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleAcceptController {
  private final ScheduleAcceptService scheduleAcceptService;

  @PatchMapping("/schedule-applicant/{scheduleIndex}")
  public ResponseEntity accept(@PathVariable Long scheduleIndex, @RequestBody CompanyUserIndexPairRequest companyUserIndexPairRequest) {
    scheduleAcceptService.accept(scheduleIndex, companyUserIndexPairRequest);
    return ResponseEntity.ok().build();
  }
}