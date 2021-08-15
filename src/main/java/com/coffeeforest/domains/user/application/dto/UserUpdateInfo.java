package com.coffeeforest.domains.user.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserUpdateInfo {
  private Long userIndex;
  private String name;
  private String password;
  private String phone;
  private String address;
}
