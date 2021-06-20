package com.coffeeforest.domains.attendance.application;

import com.coffeeforest.domains.attendance.domain.AttendanceEntity;
import com.coffeeforest.domains.attendance.domain.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceFindService {
  private final AttendanceRepository attendanceRepository;

  public Optional<AttendanceEntity> findByDateAndCompanyIndexAndUserIndex(
      LocalDate date, Long companyIndex, Long userIndex) {
    return attendanceRepository.findByDateAndCompanyEntityIdAndUserEntityId(
        date, companyIndex, userIndex);
  }

  public boolean existsByDateAndCompanyIndexAndUserIndex(
      LocalDate date, Long companyIndex, Long userIndex) {
    return attendanceRepository.existsByDateAndCompanyEntityIdAndUserEntityId(
        date, companyIndex, userIndex);
  }
}
