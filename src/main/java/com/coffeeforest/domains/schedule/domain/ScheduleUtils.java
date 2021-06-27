package com.coffeeforest.domains.schedule.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleUtils {

  public static final int WORK_RANGE = 7;

  public int getMonthValue(LocalDateTime dateTime) {
    return dateTime.getMonthValue();
  }

  public int getWeekStartDateValue(LocalDateTime dateTime) {
    return dateTime.with(DayOfWeek.MONDAY).getDayOfMonth();
  }

  public int getWeekEndDateValue(LocalDateTime dateTime) {
    return dateTime.with(DayOfWeek.FRIDAY).getDayOfMonth();
  }

  public LocalDate getWeekStartDate(LocalDateTime dateTime) {
    return LocalDate.of(
        dateTime.getYear(), getMonthValue(dateTime), getWeekStartDateValue(dateTime));
  }
}
