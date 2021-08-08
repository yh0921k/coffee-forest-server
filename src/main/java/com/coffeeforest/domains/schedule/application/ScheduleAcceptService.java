package com.coffeeforest.domains.schedule.application;

import com.coffeeforest.domains.admin.application.AdminVerificationService;
import com.coffeeforest.domains.admin.application.dto.AdminVerificationInfo;
import com.coffeeforest.domains.schedule.domain.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleAcceptService {

  private final AdminVerificationService adminVerificationService;
  private final ScheduleFindService scheduleFindService;

  @Transactional
  public void accept(Long scheduleIndex, AdminVerificationInfo adminVerificationInfo) {
    adminVerificationService.verify(adminVerificationInfo);
    ScheduleEntity scheduleEntity = scheduleFindService.findByIndex(scheduleIndex);
    scheduleEntity.accept();
  }
}
