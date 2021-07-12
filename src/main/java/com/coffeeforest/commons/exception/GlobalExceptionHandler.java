package com.coffeeforest.commons.exception;

import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.commons.exception.detail.UnknownException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidArgumentException.class)
  public ResponseEntity<ExceptionResponse> handleInvalidArgumentException(
      HttpServletRequest request, InvalidArgumentException exception) {

    System.out.println("handleInvalidArgumentException() : " + exception.getMessage());
    ExceptionResponse response = ExceptionResponse.of(request, exception);

    return ResponseEntity.status(response.getValue()).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleException(
      HttpServletRequest request, Exception exception) {

    System.out.println("handleInvalidArgumentException() : " + exception.getMessage());
    exception.printStackTrace();
    ExceptionResponse response =
        ExceptionResponse.of(request, new UnknownException(ExceptionState.UNKNOWN));

    return ResponseEntity.badRequest().body(response);
  }
}
