package com.coffeeforest.commons.exception.detail;

import com.coffeeforest.commons.exception.CustomException;
import com.coffeeforest.commons.exception.ExceptionState;
import lombok.Getter;

@Getter
public class UserAuthenticationException extends RuntimeException implements CustomException {
  private final ExceptionState state;

  public UserAuthenticationException(ExceptionState state) {
    super(state.getMessage());
    this.state = state;
  }

  public UserAuthenticationException(ExceptionState state, String message) {
    super(message);
    this.state = state;
  }
}
