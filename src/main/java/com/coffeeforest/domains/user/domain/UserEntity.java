package com.coffeeforest.domains.user.domain;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.UserAuthenticationException;
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

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String phone;

  private String address;
  private Position position;
  private String profileImage;

  @Builder
  public UserEntity(String email, String password, String name, String phone, String address) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phone = phone;
    this.address = address;
  }

  @PrePersist
  public void prePersist() {
    this.position = this.position == null ? Position.UNKNOWN : this.position;
    this.profileImage = this.profileImage == null ? "" : this.profileImage;
  }

  public void isValidPosition(Position position) {
    if (!this.position.equals(position)) {
      throw new UserAuthenticationException(ExceptionState.AUTHENTICATION_FAILED, "Required admin");
    }
  }

  public UserEntity updatePosition(Position position) {
    this.position = position;
    return this;
  }

  public void update(String name, String phone, String address) {
    this.name = name;
    this.phone = phone;
    this.address = address;
  }

  public UserEntity profileImages(String pathList) {
    this.profileImage = pathList;
    return this;
  }
}
