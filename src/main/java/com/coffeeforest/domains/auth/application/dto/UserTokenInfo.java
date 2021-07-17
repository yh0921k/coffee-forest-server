package com.coffeeforest.domains.auth.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserTokenInfo {
  private Long userIndex;
  private String accessToken;
  private String refreshToken;
}
