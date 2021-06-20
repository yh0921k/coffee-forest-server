package com.coffeeforest.domains.attendance.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
  Optional<AttendanceEntity> findByDateAndCompanyEntityIdAndUserEntityId(
      LocalDate date, Long companyIndex, Long userIndex);

  boolean existsByDateAndCompanyEntityIdAndUserEntityId(
      LocalDate date, Long companyIndex, Long userIndex);
}
