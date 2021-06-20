package com.coffeeforest.commons.exception;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class ErrorResponseMessage {
  private int value;
  private String message;
  private String requestURI;
  private String queryString;
  private String requestMethod;
}
