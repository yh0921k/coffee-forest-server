package com.coffeeforest.domains.user.application;

import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.schedule.application.ScheduleFindService;
import com.coffeeforest.domains.schedule.application.dto.ScheduleInfo;
import com.coffeeforest.domains.schedule.domain.ScheduleUtils;
import com.coffeeforest.domains.user.application.dto.UserSignInRequest;
import com.coffeeforest.domains.user.application.dto.UserSignInResponse;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.work.application.WorkFindService;
import com.coffeeforest.domains.work.domain.WorkEntity;
import com.coffeeforest.domains.work.domain.WorkStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserSignInService {
  private final UserFindService userFindService;
  private final WorkFindService workFindService;
  private final ScheduleFindService scheduleFindService;
  private final ScheduleUtils scheduleUtils;

  public UserSignInResponse signIn(UserSignInRequest userSignInRequest) {
    UserEntity userEntity =
        userFindService.findByEmailAndPassword(
            userSignInRequest.getEmail(), userSignInRequest.getPassword());

    WorkEntity workEntity =
        workFindService.findByUserIndexAndWorkStatus(userEntity.getId(), WorkStatus.WORKING);
    CompanyEntity companyEntity = workEntity.getCompanyEntity();

    LocalDateTime now = LocalDateTime.now();
    LocalDate startDate = scheduleUtils.getWeekStartDate(now);

    Long userIndex = userEntity.getId();
    Long companyIndex = companyEntity.getId();
    Map<LocalDate, List<ScheduleInfo>> dateScheduleInfoListMap =
        scheduleFindService.findWeekSchedule(
            userIndex, companyIndex, startDate, ScheduleUtils.WORK_RANGE);

    return UserSignInResponse.builder()
        .companyIndex(companyIndex)
        .userIndex(userIndex)
        .year(now.getYear())
        .month(scheduleUtils.getMonthValue(now))
        .weekNumber(now.get(WeekFields.of(Locale.getDefault()).weekOfMonth()))
        .weekStartDate(scheduleUtils.getWeekStartDateValue(now))
        .weekEndDate(scheduleUtils.getWeekEndDateValue(now))
        .name(userEntity.getName())
        .position(userEntity.getPosition())
        .scheduleList(dateScheduleInfoListMap)
        .build();
  }
}
