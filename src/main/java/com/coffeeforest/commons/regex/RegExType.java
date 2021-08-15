package com.coffeeforest.commons.regex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegExType {
  PASSWORD("((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,})"),
  BUSINESS_NUMBER("^\\d{3}-\\d{2}-\\d{5}$"),
  PHONE_NUMBER("^\\d{3}-\\d{4}-\\d{4}$");

  private final String pattern;
}
