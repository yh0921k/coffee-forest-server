package com.coffeeforest.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponseMessage> handleIllegalArgumentException(
      HttpServletRequest httpServletRequest, IllegalArgumentException illegalArgumentException) {

    String errorMessage = illegalArgumentException.getMessage();
    System.out.println("Server Error > [IllegalArgumentException.class] : " + errorMessage);
    illegalArgumentException.printStackTrace();

    return ResponseEntity.badRequest()
        .body(
            ErrorResponseMessage.builder()
                .value(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .requestURI(httpServletRequest.getRequestURI())
                .queryString(httpServletRequest.getQueryString())
                .requestMethod(httpServletRequest.getMethod())
                .build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseMessage> handleException(
      HttpServletRequest httpServletRequest, Exception exception) {

    String errorMessage = exception.getMessage();
    System.out.println("Server Error > [Exception.class] : " + errorMessage);
    exception.printStackTrace();

    return new ResponseEntity<>(
        ErrorResponseMessage.builder()
            .value(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(
                "If you see this message, you should contact the backend developer immediately\n\n"
                    + "[Error Message]\n"
                    + errorMessage)
            .requestURI(httpServletRequest.getRequestURI())
            .queryString(httpServletRequest.getQueryString())
            .requestMethod(httpServletRequest.getMethod())
            .build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
