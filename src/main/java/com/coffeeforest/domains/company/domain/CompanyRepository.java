package com.coffeeforest.domains.company.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

  boolean existsByBusinessNumber(String businessNumber);

  Optional<CompanyEntity> findByName(String name);
}
