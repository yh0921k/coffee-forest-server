package com.coffeeforest.commons.exception;

public interface CustomException {
  ExceptionState getState();

  String getMessage();
}
