package com.coffeeforest.domains.schedule.application;

import com.coffeeforest.domains.company.application.CompanyFindService;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.schedule.application.dto.ScheduleSaveRequest;
import com.coffeeforest.domains.schedule.application.dto.ScheduleSaveResponse;
import com.coffeeforest.domains.schedule.domain.ScheduleEntity;
import com.coffeeforest.domains.schedule.domain.ScheduleRepository;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleSaveService {

  private final UserFindService userFindService;
  private final CompanyFindService companyFindService;
  private final ScheduleRepository scheduleRepository;

  public ScheduleSaveResponse save(ScheduleSaveRequest scheduleSaveRequest) {
    UserEntity userEntity = userFindService.findById(scheduleSaveRequest.getUserIndex());
    CompanyEntity companyEntity =
        companyFindService.findById(scheduleSaveRequest.getCompanyIndex());

    ScheduleEntity scheduleEntity =
        ScheduleEntity.builder()
            .date(scheduleSaveRequest.getDate())
            .startTime(scheduleSaveRequest.getStartTime())
            .endTime(scheduleSaveRequest.getEndTime())
            .scheduleStatus(scheduleSaveRequest.getScheduleStatus())
            .userEntity(userEntity)
            .companyEntity(companyEntity)
            .build();
    scheduleRepository.save(scheduleEntity);

    return ScheduleSaveResponse.builder()
        .userIndex(userEntity.getId())
        .date(scheduleEntity.getDate())
        .startTime(scheduleEntity.getStartTime())
        .endTime(scheduleEntity.getEndTime())
        .scheduleStatus(scheduleEntity.getScheduleStatus())
        .build();
  }
}
