package com.coffeeforest.domains.user.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSaveRequest {
  private String email;
  private String password;
  private String name;
  private String phone;
  private String address;
}
