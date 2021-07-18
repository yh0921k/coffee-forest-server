package com.coffeeforest.domains.company_applicant.domain;

import com.coffeeforest.domains.BaseTimeEntity;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company_applicant")
@Entity
public class CompanyApplicantEntity extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String address;

  @Column(unique = true, nullable = false)
  private String businessNumber;

  private CompanyApplicantStatus companyApplicantStatus;
  private String rejectReason;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private UserEntity owner;

  @ManyToOne
  @JoinColumn(name = "acceptor_id")
  private UserEntity acceptor;

  @Builder
  public CompanyApplicantEntity(
      String name, String address, String businessNumber, UserEntity owner) {
    this.name = name;
    this.address = address;
    this.businessNumber = businessNumber;
    this.owner = owner;
    this.companyApplicantStatus = CompanyApplicantStatus.WAIT;
  }
}
