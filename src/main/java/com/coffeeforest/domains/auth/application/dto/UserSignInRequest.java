package com.coffeeforest.domains.auth.application.dto;

import lombok.Getter;

@Getter
public class UserSignInRequest {
  private String email;
  private String password;
}
