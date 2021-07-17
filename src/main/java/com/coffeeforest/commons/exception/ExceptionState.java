package com.coffeeforest.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionState {
  UNKNOWN(400, HttpStatus.BAD_REQUEST, "Unknown", "Contact Backend Developer"),
  INVALID_ARGUMENT(400, HttpStatus.BAD_REQUEST, "P0001", "Invalid Argument"),

  AUTHENTICATION_FAILED(401, HttpStatus.UNAUTHORIZED, "A0001", "Invalid Authentication"),
  INVALID_TOKEN(401, HttpStatus.UNAUTHORIZED, "A0002", "Invalid Auth Token"),
  FORCE_REFRESH(401, HttpStatus.UNAUTHORIZED, "A0003", "Sign-in Again");

  private final int value;
  private final HttpStatus httpStatus;
  private final String code;
  private final String message;
}

