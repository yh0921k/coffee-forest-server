package com.coffeeforest.domains.auth.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.commons.regex.RegExType;
import com.coffeeforest.commons.regex.RegExUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

  private final PasswordEncoder passwordEncoder;
  private final RegExUtils regExUtils;

  private void validate(String password) {
    if (!regExUtils.validate(RegExType.PASSWORD, password)) {
      throw new InvalidArgumentException(
          ExceptionState.INVALID_ARGUMENT, "Invalid Password Format");
    }
  }

  public String encode(String password) {
    this.validate(password);
    return passwordEncoder.encode(password);
  }

  public void matches(String password, String requestPassword) {
    if (!passwordEncoder.matches(requestPassword, password)) {
      throw new InvalidArgumentException(ExceptionState.INVALID_ARGUMENT, "Invalid Password");
    }
  }
}
