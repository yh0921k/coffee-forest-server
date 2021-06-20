package com.coffeeforest.domains.attendance.application;

import com.coffeeforest.domains.attendance.application.dto.AttendanceSaveRequest;
import com.coffeeforest.domains.attendance.application.dto.AttendanceSaveResponse;
import com.coffeeforest.domains.attendance.domain.AttendanceEntity;
import com.coffeeforest.domains.attendance.domain.AttendanceRepository;
import com.coffeeforest.domains.attendance.domain.AttendanceStatus;
import com.coffeeforest.domains.company.application.CompanyFindService;
import com.coffeeforest.domains.company.domain.CompanyEntity;
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
  private final AttendanceRepository attendanceRepository;
  private final AttendanceFindService attendanceFindService;
  private final CompanyFindService companyFindService;
  private final UserFindService userFindService;

  @Transactional
  public AttendanceSaveResponse enter(AttendanceSaveRequest attendanceSaveRequest) {
    // TODO : Custom Exception 처리 및 조금 더 나은 코드 방향 설계
    if (attendanceFindService.existsByDateAndCompanyIndexAndUserIndex(
        LocalDate.now(),
        attendanceSaveRequest.getCompanyIndex(),
        attendanceSaveRequest.getUserIndex())) {
      throw new IllegalArgumentException("Already Entered");
    }

    AttendanceEntity attendanceEntity = this.save(attendanceSaveRequest);

    LocalTime responseStartTime = attendanceEntity.getStartTime();
    LocalTime responseEndTime = attendanceEntity.getCompanyEntity().getEndTime();

    return AttendanceSaveResponse.builder()
        .date(attendanceEntity.getDate())
        .startTime(responseStartTime)
        .endTime(responseEndTime)
        .build();
  }

  @Transactional
  public AttendanceEntity save(AttendanceSaveRequest attendanceSaveRequest) {
    CompanyEntity companyEntity =
        companyFindService.findById(attendanceSaveRequest.getCompanyIndex());
    UserEntity userEntity = userFindService.findById(attendanceSaveRequest.getUserIndex());

    AttendanceEntity attendanceEntity =
        AttendanceEntity.builder()
            .date(LocalDate.now())
            .startTime(LocalTime.now())
            .attendanceStatus(AttendanceStatus.ENTER)
            .companyEntity(companyEntity)
            .userEntity(userEntity)
            .build();

    return attendanceRepository.save(attendanceEntity);
  }
}
