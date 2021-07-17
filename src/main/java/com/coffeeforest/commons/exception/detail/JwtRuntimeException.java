package com.coffeeforest.commons.exception.detail;

import com.coffeeforest.commons.exception.CustomException;
import com.coffeeforest.commons.exception.ExceptionState;
import lombok.Getter;

@Getter
public class JwtRuntimeException extends RuntimeException implements CustomException {
  private final ExceptionState state;

  public JwtRuntimeException(ExceptionState state) {
    super(state.getMessage());
    this.state = state;
  }

  public JwtRuntimeException(ExceptionState state, String message) {
    super(message);
    this.state = state;
  }
}
