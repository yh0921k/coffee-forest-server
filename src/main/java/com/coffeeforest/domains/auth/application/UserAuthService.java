package com.coffeeforest.domains.auth.application;

import com.coffeeforest.commons.jwt.AuthToken;
import com.coffeeforest.commons.jwt.JwtUtils;
import com.coffeeforest.commons.jwt.TokenType;
import com.coffeeforest.domains.auth.domain.UserTokenEntity;
import com.coffeeforest.domains.auth.domain.UserTokenRepository;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserAuthService {

  private final JwtUtils jwtUtils;
  private final UserTokenRepository userTokenRepository;

  @Transactional
  public void save(UserEntity userEntity, String refreshToken) {
    UserTokenEntity userTokenEntity =
        userTokenRepository.existsByUserEntityId(userEntity.getId())
            ? userTokenRepository
                .findByUserEntityId(userEntity.getId())
                .get()
                .refreshToken(refreshToken)
            : userTokenRepository.save(
                UserTokenEntity.builder()
                    .userEntity(userEntity)
                    .refreshToken(refreshToken)
                    .build());
  }

  public AuthToken createAccessToken(UserEntity userEntity) {
    Map<String, Object> payload = new LinkedHashMap<>();
    payload.put("email", userEntity.getEmail());
    payload.put("position", userEntity.getPosition());

    return jwtUtils.createAuthToken(payload, TokenType.ACCESS_TOKEN);
  }

  public AuthToken createRefreshToken(UserEntity userEntity) {
    Map<String, Object> payload = new LinkedHashMap<>();
    payload.put("userIndex", userEntity.getId());

    return jwtUtils.createAuthToken(payload, TokenType.REFRESH_TOKEN);
  }
}
