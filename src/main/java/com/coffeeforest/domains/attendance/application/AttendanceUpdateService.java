package com.coffeeforest.domains.attendance.application;

import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeRequest;
import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeResponse;
import com.coffeeforest.domains.schedule.domain.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AttendanceUpdateService {
  private final AttendanceFindService attendanceFindService;

  @Transactional
  public AttendanceTimeResponse leave(AttendanceTimeRequest attendanceTimeRequest) {
    ScheduleEntity scheduleEntity =
        attendanceFindService
            .findByDateAndCompanyIndexAndUserIndex(
                LocalDate.now(),
                attendanceTimeRequest.getCompanyIndex(),
                attendanceTimeRequest.getUserIndex())
            .orElseThrow(() -> new IllegalArgumentException("Entered History not Exists"));

    LocalTime baseEndTime = scheduleEntity.getCompanyEntity().getEndTime();
    scheduleEntity.endTime(LocalTime.now(), baseEndTime);

    return AttendanceTimeResponse.builder()
        .date(LocalDate.now())
        .startTime(scheduleEntity.getStartTime())
        .endTime(scheduleEntity.getEndTime())
        .build();
  }
}
