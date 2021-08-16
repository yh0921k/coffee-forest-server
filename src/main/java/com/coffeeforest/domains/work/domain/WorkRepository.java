package com.coffeeforest.domains.work.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<WorkEntity, Long> {
  Optional<WorkEntity> findByUserEntityIdAndCompanyEntityId(Long userIndex, Long companyIndex);

  Optional<WorkEntity> findByUserEntityIdAndWorkStatus(Long userIndex, WorkStatus workStatus);

  List<WorkEntity> findAllByCompanyEntityId(Long companyIndex);
}
