package com.coffeeforest.domains.schedule.domain;

import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "schedule")
@Entity
public class ScheduleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate date;
  private LocalTime startTime;
  private LocalTime endTime;

  private ScheduleStatus scheduleStatus;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private CompanyEntity companyEntity;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  @Builder
  public ScheduleEntity(
      LocalDate date,
      LocalTime startTime,
      LocalTime endTime,
      ScheduleStatus scheduleStatus,
      CompanyEntity companyEntity,
      UserEntity userEntity) {
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
    this.scheduleStatus = scheduleStatus;
    this.companyEntity = companyEntity;
    this.userEntity = userEntity;
  }

  public ScheduleEntity endTime(LocalTime now, LocalTime companyEndTime) {
    if (now.isBefore(companyEndTime)) {
      throw new IllegalArgumentException(
          "Current Time is before Work-Off Time("
              + companyEndTime.format(DateTimeFormatter.ofPattern("HH:mm"))
              + ")");
    }

    this.endTime = now;
    this.scheduleStatus = ScheduleStatus.LEAVE;
    return this;
  }
}