package com.coffeeforest.domains.work.domain;

import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.user.domain.UserEntity;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
  private int holidayCount;

  @Builder
  public WorkEntity(
      CompanyEntity companyEntity, UserEntity userEntity, LocalDate startDate, LocalDate endDate) {
    this.companyEntity = companyEntity;
    this.userEntity = userEntity;
    this.startDate = startDate;
    this.endDate = endDate;
    this.holidayCount = 0;
  }
}
