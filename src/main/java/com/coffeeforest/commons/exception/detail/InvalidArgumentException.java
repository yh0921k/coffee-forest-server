package com.coffeeforest.commons.exception.detail;

import com.coffeeforest.commons.exception.CustomException;
import com.coffeeforest.commons.exception.ExceptionState;
import lombok.Getter;

@Getter
public class InvalidArgumentException extends RuntimeException implements CustomException {
  private final ExceptionState state;

  public InvalidArgumentException(ExceptionState state) {
    super(state.getMessage());
    this.state = state;
  }

  public InvalidArgumentException(ExceptionState state, String message) {
    super(message);
    this.state = state;
  }
}
