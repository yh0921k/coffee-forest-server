package com.coffeeforest.commons.jwt;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TokenType {
  ACCESS_TOKEN("accessToken", 60 * 24),
  REFRESH_TOKEN("refreshToken", 60 * 24 * 7);

  private String name;
  private long expiredMinutes;

  private TokenType(String name, long expiredMinutes) {
    this.name = name;
    this.expiredMinutes = expiredMinutes;
  }

  public static long getExpiredMinutes(String tokenName) {
    return Arrays.stream(TokenType.values())
        .filter(tokenType -> tokenType.getName().equals(tokenName))
        .findAny()
        .get()
        .expiredMinutes;
  }
}
