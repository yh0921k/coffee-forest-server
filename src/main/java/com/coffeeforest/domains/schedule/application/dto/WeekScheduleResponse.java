package com.coffeeforest.domains.schedule.application.dto;

import com.coffeeforest.domains.user.domain.Position;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WeekScheduleResponse {
  private Long userIndex;
  private String email;
  private String userName;
  private Position position;
  private String profileImage;
  private String companyName;
  Map<LocalDate, List<ScheduleInfo>> scheduleInfo;
}
