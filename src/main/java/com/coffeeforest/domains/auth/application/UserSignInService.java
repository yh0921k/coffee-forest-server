package com.coffeeforest.domains.auth.application;

import com.coffeeforest.commons.jwt.AuthToken;
import com.coffeeforest.domains.auth.application.dto.UserSignInRequest;
import com.coffeeforest.domains.auth.application.dto.UserSignInResponse;
import com.coffeeforest.domains.auth.application.dto.UserTokenInfo;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignInService {
  private final UserFindService userFindService;
  private final UserAuthService userAuthService;
  private final PasswordService passwordService;

  @Transactional
  public UserSignInResponse signIn(UserSignInRequest userSignInRequest) {
    UserEntity userEntity = userFindService.findByEmail(userSignInRequest.getEmail());
    passwordService.matches(userEntity.getPassword(), userSignInRequest.getPassword());

    AuthToken accessToken = userAuthService.createAccessToken(userEntity);
    AuthToken refreshToken = userAuthService.createRefreshToken(userEntity);
    userAuthService.save(userEntity, refreshToken.getValue());

    return UserSignInResponse.builder()
        .userIndex(userEntity.getId())
        .email(userEntity.getEmail())
        .name(userEntity.getName())
        .position(userEntity.getPosition())
        .profileImage(userEntity.getProfileImage())
        .userTokenInfo(
            UserTokenInfo.builder()
                .userIndex(userEntity.getId())
                .accessToken(accessToken.getValue())
                .refreshToken(refreshToken.getValue())
                .build())
        .build();
  }
}