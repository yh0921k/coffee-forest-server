package com.coffeeforest.domains.attendance.domain;

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
@Table(name = "attendance")
@Entity
public class AttendanceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate date;
  private LocalTime startTime;
  private LocalTime endTime;

  private AttendanceStatus attendanceStatus;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private CompanyEntity companyEntity;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  @Builder
  public AttendanceEntity(
      LocalDate date,
      LocalTime startTime,
      LocalTime endTime,
      AttendanceStatus attendanceStatus,
      CompanyEntity companyEntity,
      UserEntity userEntity) {
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
    this.attendanceStatus = attendanceStatus;
    this.companyEntity = companyEntity;
    this.userEntity = userEntity;
  }
}
