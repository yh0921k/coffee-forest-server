package com.coffeeforest.domains.work_applicant.domain;

import com.coffeeforest.domains.BaseTimeEntity;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.work.domain.WorkStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "work_applicant")
@Entity
public class WorkApplicantEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private CompanyEntity companyEntity;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  private WorkStatus workStatus;

  @Builder
  public WorkApplicantEntity(CompanyEntity companyEntity, UserEntity userEntity) {
    this.companyEntity = companyEntity;
    this.userEntity = userEntity;
    this.workStatus = WorkStatus.WAITING;
  }
}
