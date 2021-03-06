package com.coffeeforest.domains.user.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.commons.regex.RegExType;
import com.coffeeforest.commons.regex.RegExUtils;
import com.coffeeforest.domains.auth.application.PasswordService;
import com.coffeeforest.domains.user.application.dto.UserSaveRequest;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

  private final UserRepository userRepository;
  private final PasswordService passwordService;
  private final RegExUtils regExUtils;

  @Transactional
  public void signUp(UserSaveRequest userSaveRequest) {
    String password = userSaveRequest.getPassword();

    String email = userSaveRequest.getEmail();
    if (userRepository.existsByEmail(email)) {
      throw new InvalidArgumentException(
          ExceptionState.INVALID_ARGUMENT, "Email Already Registered");
    }

    if(!regExUtils.validate(RegExType.PHONE_NUMBER, userSaveRequest.getPhone())) {
      throw new InvalidArgumentException(
              ExceptionState.INVALID_ARGUMENT, "Invalid Phone Number Format");
    }

    String encodedPassword = passwordService.encode(password);
    userRepository.save(
        UserEntity.builder()
            .email(email)
            .password(encodedPassword)
            .name(userSaveRequest.getName())
            .phone(userSaveRequest.getPhone())
            .address(userSaveRequest.getAddress())
            .build());
  }

  public String isDuplicated(String email) {
    String result = userRepository.existsByEmail(email) ? "Duplicated" : "Available";
    return result;
  }
}
