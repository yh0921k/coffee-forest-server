package com.coffeeforest.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorStatus {
  EXAMPLE(404, HttpStatus.NOT_FOUND, "Resource Not Found");

  private final int value;
  private final HttpStatus status;
  private final String message;
}
