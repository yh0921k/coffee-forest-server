package com.coffeeforest.domains.auth.application.dto;

import com.coffeeforest.domains.user.domain.Position;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSignInResponse {
  private Long userIndex;
  private String email;
  private String name;
  private Position position;
  private String profileImage;
  private UserTokenInfo userTokenInfo;
}
