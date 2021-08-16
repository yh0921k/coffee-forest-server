package com.coffeeforest.domains.user.application.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SimpleUserInfo {
  private Long userIndex;
  private String name;
  private String email;
  private String profileImage;
  private LocalTime workStartTime;
  private LocalTime workEndTime;
  private int fullDayOffCount;
}
