package com.coffeeforest.domains.attendance.presentation;

import com.coffeeforest.domains.attendance.application.AttendanceSaveService;
import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeRequest;
import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AttendanceSaveController {
  private final AttendanceSaveService attendanceSaveService;

  @PostMapping("/attendance")
  public ResponseEntity<AttendanceTimeResponse> enter(
      @RequestBody AttendanceTimeRequest attendanceTimeRequest) {
    return ResponseEntity.ok(attendanceSaveService.enter(attendanceTimeRequest));
  }
}
