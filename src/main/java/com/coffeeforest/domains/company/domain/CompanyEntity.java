package com.coffeeforest.domains.company.domain;

import com.coffeeforest.domains.BaseTimeEntity;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company")
@Entity
public class CompanyEntity extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String address;

  @Column(unique = true)
  private String businessNumber;

  @OneToOne
  @JoinColumn(name = "owner_id")
  private UserEntity owner;

  @OneToOne
  @JoinColumn(name = "company_config_id")
  private CompanyConfigEntity companyConfigEntity;

  @Builder
  public CompanyEntity(String name, String address, String businessNumber, UserEntity owner, CompanyConfigEntity companyConfigEntity) {
    this.name = name;
    this.address = address;
    this.businessNumber = businessNumber;
    this.owner = owner;
    this.companyConfigEntity = companyConfigEntity;
  }
}
