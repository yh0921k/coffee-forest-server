package com.coffeeforest.domains.company.domain;

import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company")
@Entity
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String address;
  private String businessNumber;

  @OneToOne
  @JoinColumn(name = "owner_id")
  private UserEntity owner;

  @Builder
  public CompanyEntity(String name, String address, String businessNumber, UserEntity owner) {
    this.name = name;
    this.address = address;
    this.businessNumber = businessNumber;
    this.owner = owner;
  }
}
