package com.coffeeforest.domains.work.application;

import com.coffeeforest.domains.work.application.dto.WorkSaveRequest;
import com.coffeeforest.domains.work.domain.WorkEntity;
import com.coffeeforest.domains.work.domain.WorkRepository;
import java.time.LocalDate;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkSaveService {

  private final WorkRepository workRepository;

  @Transactional
  public WorkEntity save(WorkSaveRequest workSaveRequest) {
    WorkEntity workEntity =
        WorkEntity.builder()
            .companyEntity(workSaveRequest.getCompanyEntity())
            .userEntity(workSaveRequest.getUserEntity())
            .startDate(LocalDate.now())
            .endDate(null)
            .build();

    return workRepository.save(workEntity);
  }
}
