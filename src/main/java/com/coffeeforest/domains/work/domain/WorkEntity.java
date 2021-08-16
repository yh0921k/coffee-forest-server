package com.coffeeforest.domains.work.domain;

import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "work")
@Entity
public class WorkEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private CompanyEntity companyEntity;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  private LocalDate startDate;
  private LocalDate endDate;

  private boolean flexibleState;
  private LocalTime workStartTime;
  private LocalTime workEndTime;
  private int fullDayOffCount;

  private WorkStatus workStatus;

  @Builder
  public WorkEntity(
      CompanyEntity companyEntity,
      UserEntity userEntity,
      LocalDate startDate,
      LocalDate endDate,
      LocalTime workStartTime,
      LocalTime workEndTime,
      WorkStatus workStatus) {
    this.companyEntity = companyEntity;
    this.userEntity = userEntity;
    this.startDate = startDate;
    this.endDate = endDate;
    this.flexibleState = false;
    this.workStartTime = workStartTime;
    this.workEndTime = workEndTime;
    this.fullDayOffCount = 0;
    this.workStatus = workStatus;
  }

  public WorkEntity flexibleInfo(boolean flexibleState, LocalTime workStartTime, LocalTime workEndTime, int fullDayOffCount) {
    this.flexibleState = flexibleState;
    this.workStartTime = workStartTime;
    this.workEndTime = workEndTime;
    this.fullDayOffCount = fullDayOffCount;

    return this;
  }
}
