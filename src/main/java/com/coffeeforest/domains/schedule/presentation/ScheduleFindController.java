package com.coffeeforest.domains.schedule.presentation;

import com.coffeeforest.domains.schedule.application.ScheduleFindService;
import com.coffeeforest.domains.schedule.application.dto.MonthScheduleInfo;
import com.coffeeforest.domains.schedule.application.dto.ScheduleFindRequest;
import com.coffeeforest.domains.schedule.application.dto.ScheduleSaveResponse;
import com.coffeeforest.domains.schedule.application.dto.WeekScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleFindController {

  private final ScheduleFindService scheduleFindService;

  @PostMapping("/schedule/week")
  public ResponseEntity<WeekScheduleResponse> find(
      @RequestBody ScheduleFindRequest scheduleFindRequest) {
    WeekScheduleResponse weekScheduleResponse = scheduleFindService.find(scheduleFindRequest);
    return ResponseEntity.ok(weekScheduleResponse);
  }

  @PostMapping("/schedule-applicant")
  public ResponseEntity<List<ScheduleSaveResponse>> findByScheduleStatus(
      @RequestBody ScheduleFindRequest scheduleFindRequest) {
    List<ScheduleSaveResponse> scheduleSaveResponseList =
        scheduleFindService.findAllByScheduleStatus(scheduleFindRequest);
    return ResponseEntity.ok(scheduleSaveResponseList);
  }

  @PostMapping("/schedule/month")
  public ResponseEntity<List<MonthScheduleInfo>> findMonthList(
      @RequestBody ScheduleFindRequest scheduleFindRequest) {
    List<MonthScheduleInfo> monthScheduleInfoList =
        scheduleFindService.findMonthList(scheduleFindRequest);
    return ResponseEntity.ok(monthScheduleInfoList);
  }
}
