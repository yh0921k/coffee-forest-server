package com.coffeeforest.domains.user.application;

import com.coffeeforest.domains.user.application.dto.UserSignInRequest;
import com.coffeeforest.domains.user.application.dto.UserSignInResponse;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.work.application.WorkFindService;
import com.coffeeforest.domains.work.domain.WorkEntity;
import com.coffeeforest.domains.work.domain.WorkStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserSignInService {
  private final UserFindService userFindService;
  private final WorkFindService workFindService;

  public UserSignInResponse signIn(UserSignInRequest userSignInRequest) {
    UserEntity userEntity =
        userFindService.findByEmailAndPassword(
            userSignInRequest.getEmail(), userSignInRequest.getPassword());

    WorkEntity workEntity =
        workFindService.findByUserIndexAndWorkStatus(userEntity.getId(), WorkStatus.WORKING);

    LocalDateTime now = LocalDateTime.now();
    return UserSignInResponse.builder()
        .companyIndex(workEntity.getCompanyEntity().getId())
        .userIndex(userEntity.getId())
        .year(now.getYear())
        .month(now.getMonth().getValue())
        .weekNumber(now.get(WeekFields.of(Locale.getDefault()).weekOfMonth()))
        .weekStartDate(now.with(DayOfWeek.MONDAY).getDayOfMonth())
        .weekEndDate(now.with(DayOfWeek.FRIDAY).getDayOfMonth())
        .name(userEntity.getName())
        .position(userEntity.getPosition())
        .build();
  }
}
