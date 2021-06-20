package com.coffeeforest.domains.user.domain;

import com.coffeeforest.domains.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class UserEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;

  private String password;
  private Position position;
  private String name;
  private String phone;
  private String address;

  @Builder
  public UserEntity(
      String email, String password, Position position, String name, String phone, String address) {
    this.email = email;
    this.password = password;
    this.position = position;
    this.name = name;
    this.phone = phone;
    this.address = address;
  }
}
