package com.coffeeforest.domains.schedule.presentation;

import com.coffeeforest.domains.schedule.application.ScheduleRejectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleRejectController {
  private final ScheduleRejectService scheduleRejectService;

  @DeleteMapping("/schedule-applicant/{scheduleIndex}")
  public ResponseEntity reject(@PathVariable Long scheduleIndex) {
    scheduleRejectService.reject(scheduleIndex);
    return ResponseEntity.ok().build();
  }
}
