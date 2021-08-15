package com.coffeeforest.domains.user.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SimpleUserInfo {
  private Long userIndex;
  private String name;
  private String profileImage;
}
