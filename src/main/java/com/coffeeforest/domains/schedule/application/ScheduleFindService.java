package com.coffeeforest.domains.schedule.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.schedule.application.dto.*;
import com.coffeeforest.domains.schedule.domain.ScheduleEntity;
import com.coffeeforest.domains.schedule.domain.ScheduleRepository;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.application.dto.SimpleUserInfo;
import com.coffeeforest.domains.user.domain.Position;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.work.application.WorkFindService;
import com.coffeeforest.domains.work.domain.WorkEntity;
import com.coffeeforest.domains.work.domain.WorkStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleFindService {
  private final ScheduleRepository scheduleRepository;
  private final UserFindService userFindService;
  private final WorkFindService workFindService;

  public ScheduleEntity findByIndex(Long scheduleIndex) {
    return scheduleRepository
        .findById(scheduleIndex)
        .orElseThrow(
            () ->
                new InvalidArgumentException(
                    ExceptionState.INVALID_ARGUMENT, "Invalid Schedule Index"));
  }

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
                                .title(scheduleEntity.getTitle())
                                .startTime(scheduleEntity.getStartTime())
                                .endTime(scheduleEntity.getEndTime())
                                .scheduleType(scheduleEntity.getScheduleType())
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

    Optional<CompanyEntity> companyEntity =
        workFindService
            .findByUserIndexAndWorkStatus(userEntity.getId(), WorkStatus.WORKING)
            .map(entity -> entity.getCompanyEntity());

    return WeekScheduleResponse.builder()
        .userIndex(userEntity.getId())
        .email(userEntity.getEmail())
        .userName(userEntity.getName())
        .position(userEntity.getPosition())
        .profileImage(userEntity.getProfileImage())
        .companyName(companyEntity.isPresent() ? companyEntity.get().getName() : "")
        .scheduleInfo(dateScheduleInfoMap)
        .build();
  }

  public List<ScheduleSaveResponse> findAllByScheduleStatus(
      ScheduleFindRequest scheduleFindRequest) {
    WorkEntity workEntity =
        workFindService.findByUserIndexAndCompanyIndex(
            scheduleFindRequest.getUserIndex(), scheduleFindRequest.getCompanyIndex());
    workEntity.getUserEntity().isValidPosition(Position.ADMIN);

    List<ScheduleEntity> scheduleEntityList =
        scheduleRepository.findAllByCompanyEntityIdAndAccepted(
            scheduleFindRequest.getCompanyIndex(), false);

    List<ScheduleSaveResponse> scheduleSaveResponseList =
        scheduleEntityList.stream()
            .map(
                scheduleEntity -> {
                  UserEntity userEntity = scheduleEntity.getUserEntity();
                  return ScheduleSaveResponse.builder()
                      .userIndex(userEntity.getId())
                      .scheduleIndex(scheduleEntity.getId())
                      .userName(userEntity.getName())
                      .title(scheduleEntity.getTitle())
                      .date(scheduleEntity.getDate())
                      .startTime(scheduleEntity.getStartTime())
                      .endTime(scheduleEntity.getEndTime())
                      .scheduleType(scheduleEntity.getScheduleType())
                      .build();
                })
            .collect(Collectors.toList());

    return scheduleSaveResponseList;
  }

  @Transactional
  public List<MonthScheduleInfo> findMonthList(ScheduleFindRequest scheduleFindRequest) {
    List<ScheduleEntity> scheduleEntityList =
        scheduleRepository.findAllByCompanyEntityIdAndDate(
            scheduleFindRequest.getCompanyIndex(), scheduleFindRequest.getStartDate());

    List<MonthScheduleInfo> monthScheduleInfoList =
        scheduleEntityList.stream()
            .map(
                scheduleEntity -> {
                  UserEntity userEntity = scheduleEntity.getUserEntity();

                  return MonthScheduleInfo.builder()
                      .simpleUserInfo(
                          SimpleUserInfo.builder()
                              .userIndex(userEntity.getId())
                              .name(userEntity.getName())
                              .email("")
                              .profileImage(userEntity.getProfileImage())
                              .build())
                      .scheduleInfo(
                          ScheduleInfo.builder()
                              .scheduleIndex(scheduleEntity.getId())
                              .scheduleType(scheduleEntity.getScheduleType())
                              .startTime(scheduleEntity.getStartTime())
                              .endTime(scheduleEntity.getEndTime())
                              .title(scheduleEntity.getTitle())
                              .build())
                      .build();
                })
            .collect(Collectors.toList());

    return monthScheduleInfoList;
  }
}
