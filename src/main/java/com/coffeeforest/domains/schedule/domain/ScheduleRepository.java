package com.coffeeforest.domains.schedule.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
  Optional<ScheduleEntity> findByDateAndCompanyEntityIdAndUserEntityId(
      LocalDate date, Long companyIndex, Long userIndex);

  boolean existsByDateAndCompanyEntityIdAndUserEntityId(
      LocalDate date, Long companyIndex, Long userIndex);
}
