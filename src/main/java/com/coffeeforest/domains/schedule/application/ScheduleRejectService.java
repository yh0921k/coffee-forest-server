package com.coffeeforest.domains.schedule.application;

import com.coffeeforest.domains.admin.application.AdminVerificationService;
import com.coffeeforest.domains.admin.application.dto.AdminVerificationInfo;
import com.coffeeforest.domains.schedule.domain.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleRejectService {

  private final AdminVerificationService adminVerificationService;
  private final ScheduleRepository scheduleRepository;

  @Transactional
  public void reject(Long scheduleIndex, AdminVerificationInfo adminVerificationInfo) {
    adminVerificationService.verify(adminVerificationInfo);
    scheduleRepository.deleteById(scheduleIndex);
  }
}
