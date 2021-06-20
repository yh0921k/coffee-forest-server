package com.coffeeforest.domains.user.application;

import com.coffeeforest.domains.user.application.dto.UserSignInRequest;
import com.coffeeforest.domains.user.application.dto.UserSignInResponse;
import com.coffeeforest.domains.user.domain.UserEntity;
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

  public UserSignInResponse signIn(UserSignInRequest userSignInRequest) {
    UserEntity userEntity =
        userFindService.findByEmailAndPassword(
            userSignInRequest.getEmail(), userSignInRequest.getPassword());

    LocalDateTime now = LocalDateTime.now();
    return UserSignInResponse.builder()
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
