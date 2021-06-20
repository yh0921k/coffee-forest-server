package com.coffeeforest.domains.attendance.application;

import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeRequest;
import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeResponse;
import com.coffeeforest.domains.attendance.domain.AttendanceEntity;
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
    AttendanceEntity attendanceEntity =
        attendanceFindService
            .findByDateAndCompanyIndexAndUserIndex(
                LocalDate.now(),
                attendanceTimeRequest.getCompanyIndex(),
                attendanceTimeRequest.getUserIndex())
            .orElseThrow(() -> new IllegalArgumentException("Entered History not Exists"));

    LocalTime baseEndTime = attendanceEntity.getCompanyEntity().getEndTime();
    attendanceEntity.endTime(LocalTime.now(), baseEndTime);

    return AttendanceTimeResponse.builder()
        .date(LocalDate.now())
        .startTime(attendanceEntity.getStartTime())
        .endTime(attendanceEntity.getEndTime())
        .build();
  }
}
