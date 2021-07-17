package com.coffeeforest.domains.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends
    JpaRepository<UserTokenEntity, Long> {
  boolean existsByUserEntityId(Long userIndex);

  Optional<UserTokenEntity> findByUserEntityId(Long userIndex);

  void deleteByUserEntityId(Long userIndex);
}
