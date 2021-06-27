package com.coffeeforest.domains.schedule.presentation;

import com.coffeeforest.domains.schedule.application.ScheduleSaveService;
import com.coffeeforest.domains.schedule.application.dto.ScheduleSaveRequest;
import com.coffeeforest.domains.schedule.application.dto.ScheduleSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleSaveController {

  private final ScheduleSaveService scheduleSaveService;

  @PostMapping("/schedule")
  public ResponseEntity<ScheduleSaveResponse> save(
      @RequestBody ScheduleSaveRequest scheduleSaveRequest) {
    return ResponseEntity.ok(scheduleSaveService.save(scheduleSaveRequest));
  }
}
