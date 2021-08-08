package com.coffeeforest.domains.schedule.application;

import com.coffeeforest.domains.schedule.application.dto.ScheduleFindRequest;
import com.coffeeforest.domains.schedule.application.dto.ScheduleInfo;
import com.coffeeforest.domains.schedule.application.dto.WeekScheduleResponse;
import com.coffeeforest.domains.schedule.domain.ScheduleEntity;
import com.coffeeforest.domains.schedule.domain.ScheduleRepository;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleFindService {
  private final ScheduleRepository scheduleRepository;
  private final UserFindService userFindService;

  public Map<LocalDate, List<ScheduleInfo>> findScheduleMap(
      Long userIndex, Long companyIndex, LocalDate startDate, int workRange) {
    LocalDate endDate = startDate.plusDays(workRange);

    List<ScheduleEntity> scheduleEntityList =
        scheduleRepository
            .findAllByCompanyEntityIdAndUserEntityIdAndDateBetween(
                companyIndex, userIndex, startDate, endDate)
            .orElse(Collections.emptyList());

    Map<LocalDate, List<ScheduleInfo>> dateScheduleInfoMap =
        scheduleEntityList.stream()
            .collect(
                Collectors.groupingBy(
                    ScheduleEntity::getDate,
                    Collectors.mapping(
                        scheduleEntity ->
                            ScheduleInfo.builder()
                                .scheduleIndex(scheduleEntity.getId())
                                .startTime(scheduleEntity.getStartTime())
                                .endTime(scheduleEntity.getEndTime())
                                .scheduleStatus(scheduleEntity.getScheduleStatus())
                                .build(),
                        Collectors.toList())));
    return dateScheduleInfoMap;
  }

  public WeekScheduleResponse find(ScheduleFindRequest scheduleFindRequest) {
    UserEntity userEntity = userFindService.findById(scheduleFindRequest.getUserIndex());
    Map<LocalDate, List<ScheduleInfo>> dateScheduleInfoMap =
        findScheduleMap(
            scheduleFindRequest.getUserIndex(),
            scheduleFindRequest.getCompanyIndex(),
            scheduleFindRequest.getStartDate(),
            5);

    return WeekScheduleResponse.builder()
        .userIndex(userEntity.getId())
        .userName(userEntity.getName())
        .profileImage(userEntity.getProfileImage())
        .scheduleInfo(dateScheduleInfoMap)
        .build();
  }
}
