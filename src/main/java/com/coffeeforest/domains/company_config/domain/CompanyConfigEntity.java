package com.coffeeforest.domains.company_config.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Table(name = "company_config")
@Entity
public class CompanyConfigEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalTime workStartTime;
  private LocalTime workEndTime;

  private LocalTime lunchStartTime;
  private LocalTime lunchEndTime;

  private boolean flexibleState;
  private LocalTime flexibleWorkStartTime;
  private LocalTime flexibleWorkEndTime;

  private int fullDayOffUpperYear;
  private int fullDayOffUnderYear;

  private LocalTime morningDayOffStartTime;
  private LocalTime morningDayOffEndTime;

  private LocalTime afternoonDayOffStartTime;
  private LocalTime afternoonDayOffEndTime;

  @Builder
  public CompanyConfigEntity() {
    this.workStartTime = LocalTime.of(9, 0);
    this.workEndTime = LocalTime.of(18, 0);

    this.lunchStartTime = LocalTime.of(12, 0);
    this.lunchEndTime = LocalTime.of(13, 0);

    this.flexibleState = false;
    this.flexibleWorkStartTime = LocalTime.of(8, 0);
    this.flexibleWorkEndTime = LocalTime.of(10, 0);

    this.fullDayOffUnderYear = 15;
    this.fullDayOffUnderYear = 1;

    this.morningDayOffStartTime = LocalTime.of(9, 0);
    this.morningDayOffEndTime = LocalTime.of(13, 30);

    this.afternoonDayOffStartTime = LocalTime.of(13, 30);
    this.afternoonDayOffEndTime = LocalTime.of(18, 0);
  }

  public CompanyConfigEntity update(
      LocalTime workStartTime,
      LocalTime workEndTime,
      LocalTime lunchStartTime,
      LocalTime lunchEndTime,
      boolean flexibleState,
      LocalTime flexibleWorkStartTime,
      LocalTime flexibleWorkEndTime,
      int fullDayOffUnderYear,
      int fullDayOffUpperYear,
      LocalTime morningDayOffStartTime,
      LocalTime morningDayOffEndTime,
      LocalTime afternoonDayOffStartTime,
      LocalTime afternoonDayOffEndTime) {
    this.workStartTime = workStartTime;
    this.workEndTime = workEndTime;
    this.lunchStartTime = lunchStartTime;
    this.lunchEndTime = lunchEndTime;
    this.flexibleState = flexibleState;
    this.flexibleWorkStartTime = flexibleWorkStartTime;
    this.flexibleWorkEndTime = flexibleWorkEndTime;
    this.fullDayOffUnderYear = fullDayOffUnderYear;
    this.fullDayOffUpperYear = fullDayOffUpperYear;
    this.morningDayOffStartTime = morningDayOffStartTime;
    this.morningDayOffEndTime = morningDayOffEndTime;
    this.afternoonDayOffStartTime = afternoonDayOffStartTime;
    this.afternoonDayOffEndTime = afternoonDayOffEndTime;

    return this;
  }
}
