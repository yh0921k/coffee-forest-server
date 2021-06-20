package com.coffeeforest.domains.attendance.presentation;

import com.coffeeforest.domains.attendance.application.AttendanceUpdateService;
import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeRequest;
import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AttendanceUpdateController {
  private final AttendanceUpdateService attendanceUpdateService;

  @PatchMapping("/attendance")
  public ResponseEntity<AttendanceTimeResponse> leave(
      @RequestBody AttendanceTimeRequest attendanceTimeRequest) {
    return ResponseEntity.ok(attendanceUpdateService.leave(attendanceTimeRequest));
  }
}
