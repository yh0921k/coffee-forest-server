package com.coffeeforest.domains.attendance.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
  boolean existsByDateAndCompanyEntityIdAndUserEntityId(
      LocalDate date, Long companyIndex, Long userIndex);
}
