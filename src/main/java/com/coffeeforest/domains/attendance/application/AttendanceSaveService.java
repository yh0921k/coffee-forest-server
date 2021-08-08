package com.coffeeforest.domains.attendance.application;

import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeRequest;
import com.coffeeforest.domains.attendance.application.dto.AttendanceTimeResponse;
import com.coffeeforest.domains.company.application.CompanyFindService;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.schedule.domain.ScheduleEntity;
import com.coffeeforest.domains.schedule.domain.ScheduleRepository;
import com.coffeeforest.domains.schedule.domain.ScheduleType;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AttendanceSaveService {
  private final ScheduleRepository scheduleRepository;
  private final AttendanceFindService attendanceFindService;
  private final CompanyFindService companyFindService;
  private final UserFindService userFindService;

  @Transactional
  public AttendanceTimeResponse enter(AttendanceTimeRequest attendanceTimeRequest) {
    // TODO : Custom Exception 처리 및 조금 더 나은 코드 방향 설계
    if (attendanceFindService.existsByDateAndCompanyIndexAndUserIndex(
        LocalDate.now(),
        attendanceTimeRequest.getCompanyIndex(),
        attendanceTimeRequest.getUserIndex())) {
      throw new IllegalArgumentException("Already Entered");
    }

    ScheduleEntity scheduleEntity = this.save(attendanceTimeRequest);

    LocalTime responseStartTime = scheduleEntity.getStartTime();
    LocalTime responseEndTime = scheduleEntity.getCompanyEntity().getEndTime();

    return AttendanceTimeResponse.builder()
        .date(scheduleEntity.getDate())
        .startTime(responseStartTime)
        .endTime(responseEndTime)
        .build();
  }

  @Transactional
  public ScheduleEntity save(AttendanceTimeRequest attendanceTimeRequest) {
    CompanyEntity companyEntity =
        companyFindService.findById(attendanceTimeRequest.getCompanyIndex());
    UserEntity userEntity = userFindService.findById(attendanceTimeRequest.getUserIndex());

    ScheduleEntity scheduleEntity =
        ScheduleEntity.builder()
            .title("")
            .date(LocalDate.now())
            .startTime(LocalTime.now())
            .endTime(companyEntity.getEndTime())
            .scheduleType(ScheduleType.ENTER)
            .isAccepted(true)
            .companyEntity(companyEntity)
            .userEntity(userEntity)
            .build();

    return scheduleRepository.save(scheduleEntity);
  }
}
