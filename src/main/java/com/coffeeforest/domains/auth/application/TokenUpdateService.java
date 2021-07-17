package com.coffeeforest.domains.auth.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.JwtRuntimeException;
import com.coffeeforest.commons.jwt.AuthToken;
import com.coffeeforest.commons.jwt.JwtUtils;
import com.coffeeforest.domains.auth.application.dto.UserTokenInfo;
import com.coffeeforest.domains.auth.domain.UserTokenEntity;
import com.coffeeforest.domains.auth.domain.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TokenUpdateService {
  private final UserTokenRepository userTokenRepository;
  private final JwtUtils jwtUtils;
  private final UserAuthService userAuthService;

  @Transactional
  public UserTokenInfo refresh(UserTokenInfo userTokenInfo) {
    AuthToken refreshToken = jwtUtils.convertAuthToken(userTokenInfo.getRefreshToken());
    UserTokenEntity userTokenEntity =
        userTokenRepository
            .findByUserEntityId(userTokenInfo.getUserIndex())
            .orElseThrow(() -> new IllegalArgumentException("Invalid User"));

    Long parsedIndex = -1L;
    try {
      parsedIndex = Long.parseLong(String.valueOf(refreshToken.getData().get("userIndex")));
    } catch (Exception exception) {
      throw new JwtRuntimeException(ExceptionState.FORCE_REFRESH);
    }
    userTokenEntity.validUser(parsedIndex);
    userTokenEntity.validToken(refreshToken.getValue());

    AuthToken accessToken = userAuthService.createAccessToken(userTokenEntity.getUserEntity());
    return UserTokenInfo.builder()
        .userIndex(parsedIndex)
        .accessToken(accessToken.getValue())
        .refreshToken(refreshToken.getValue())
        .build();
  }
}
