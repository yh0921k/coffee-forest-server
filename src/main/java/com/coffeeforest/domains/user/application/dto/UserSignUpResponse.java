package com.coffeeforest.domains.user.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSignUpResponse {
  private Long userIndex;
}
