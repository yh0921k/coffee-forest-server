package com.coffeeforest.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionState {
  UNKNOWN(400, HttpStatus.BAD_REQUEST, "Unknown", "Contact Backend Developer"),
  INVALID_ARGUMENT(400, HttpStatus.BAD_REQUEST, "P0001", "Invalid Argument");

  private final int value;
  private final HttpStatus httpStatus;
  private final String code;
  private final String message;
}

