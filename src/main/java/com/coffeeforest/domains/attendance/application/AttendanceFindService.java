package com.coffeeforest.domains.attendance.application;

import com.coffeeforest.domains.schedule.domain.ScheduleEntity;
import com.coffeeforest.domains.schedule.domain.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceFindService {
  private final ScheduleRepository scheduleRepository;

  public Optional<ScheduleEntity> findByDateAndCompanyIndexAndUserIndex(
      LocalDate date, Long companyIndex, Long userIndex) {
    return scheduleRepository.findByDateAndCompanyEntityIdAndUserEntityId(
        date, companyIndex, userIndex);
  }

  public boolean existsByDateAndCompanyIndexAndUserIndex(
      LocalDate date, Long companyIndex, Long userIndex) {
    return scheduleRepository.existsByDateAndCompanyEntityIdAndUserEntityId(
        date, companyIndex, userIndex);
  }
}
