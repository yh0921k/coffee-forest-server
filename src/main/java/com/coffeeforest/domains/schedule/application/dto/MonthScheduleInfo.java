package com.coffeeforest.domains.schedule.application.dto;

import com.coffeeforest.domains.user.application.dto.SimpleUserInfo;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MonthScheduleInfo {
  private SimpleUserInfo simpleUserInfo;
  private ScheduleInfo scheduleInfo;
}
