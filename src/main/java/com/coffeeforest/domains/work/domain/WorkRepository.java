package com.coffeeforest.domains.work.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkRepository extends JpaRepository<WorkEntity, Long> {
  Optional<WorkEntity> findByUserEntityIdAndWorkStatus(Long userIndex, WorkStatus workStatus);

  Optional<WorkEntity> findByUserEntityIdAndCompanyEntityId(Long userIndex, Long companyIndex);
}
