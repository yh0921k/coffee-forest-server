package com.coffeeforest.domains.work_applicant.domain;

import com.coffeeforest.domains.work.domain.WorkStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkApplicantRepository extends JpaRepository<WorkApplicantEntity, Long> {
  List<WorkApplicantEntity> findAllByCompanyEntityId(Long companyIndex);

  boolean existsByUserEntityIdAndWorkStatus(Long userIndex, WorkStatus workStatus);
}
