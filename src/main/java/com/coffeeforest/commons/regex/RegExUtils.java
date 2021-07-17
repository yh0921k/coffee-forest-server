package com.coffeeforest.commons.regex;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegExUtils {
  public boolean validate(RegExType type, String value) {
    Pattern pattern = Pattern.compile(type.getPattern());
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }
}
