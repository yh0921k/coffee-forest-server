package com.coffeeforest.domains.work.application;

import com.coffeeforest.domains.work.domain.WorkEntity;
import com.coffeeforest.domains.work.domain.WorkRepository;
import com.coffeeforest.domains.work.domain.WorkStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkFindService {
  private final WorkRepository workRepository;

  public WorkEntity findByUserIndexAndWorkStatus(Long userIndex, WorkStatus workStatus) {
    return workRepository
        .findByUserEntityIdAndWorkStatus(userIndex, workStatus)
        .orElseThrow(() -> new IllegalArgumentException("Invalid User Index"));
  }

  public WorkEntity findByUserIndexAndCompanyIndex(Long userIndex, Long companyIndex) {
    return workRepository
        .findByUserEntityIdAndCompanyEntityId(userIndex, companyIndex)
        .orElseThrow(() -> new IllegalArgumentException("Invalid User Index or Company Index"));
  }
}
