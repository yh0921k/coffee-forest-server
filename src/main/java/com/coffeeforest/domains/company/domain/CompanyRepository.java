package com.coffeeforest.domains.company.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    boolean existsByBusinessNumber(String businessNumber);
}
