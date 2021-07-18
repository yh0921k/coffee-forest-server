package com.coffeeforest.domains.company_applicant.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyApplicantRepository extends JpaRepository<CompanyApplicantEntity, Long> {
  Optional<List<CompanyApplicantEntity>> findAllByCompanyApplicantStatus(
      CompanyApplicantStatus status);
}
