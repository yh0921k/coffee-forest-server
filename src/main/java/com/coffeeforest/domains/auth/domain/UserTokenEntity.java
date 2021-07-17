package com.coffeeforest.domains.auth.domain;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.JwtRuntimeException;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_token")
@Entity
public class UserTokenEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  @Column(nullable = false)
  private String refreshToken;

  @Builder
  public UserTokenEntity(UserEntity userEntity, String refreshToken) {
    this.userEntity = userEntity;
    this.refreshToken = refreshToken;
  }

  public UserTokenEntity refreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  public void validUser(Long parsedIndex) {
    if(this.userEntity.getId() != parsedIndex) {
      throw new JwtRuntimeException(ExceptionState.FORCE_REFRESH);
    }
  }

  public void validToken(String refreshToken) {
    if (!this.refreshToken.equals(refreshToken)) {
      throw new JwtRuntimeException(ExceptionState.FORCE_REFRESH);
    }
  }
}
