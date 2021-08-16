package com.coffeeforest.domains.user.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.commons.regex.RegExType;
import com.coffeeforest.commons.regex.RegExUtils;
import com.coffeeforest.domains.auth.application.PasswordService;
import com.coffeeforest.domains.user.application.dto.UserUpdateInfo;
import com.coffeeforest.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

  private final UserFindService userFindService;
  private final PasswordService passwordService;
  private final RegExUtils regExUtils;

  @Transactional
  public UserUpdateInfo update(UserUpdateInfo userUpdateInfo) {
    UserEntity userEntity = userFindService.findById(userUpdateInfo.getUserIndex());

    if(!regExUtils.validate(RegExType.PHONE_NUMBER, userUpdateInfo.getPhone())) {
      throw new InvalidArgumentException(
              ExceptionState.INVALID_ARGUMENT, "Invalid Phone Number Format");
    }

    userEntity.update(
        userUpdateInfo.getName(),
        passwordService.encode(userUpdateInfo.getPassword()),
        userUpdateInfo.getPhone(),
        userUpdateInfo.getAddress());

    return UserUpdateInfo.builder()
        .userIndex(userEntity.getId())
        .name(userEntity.getName())
        .password("")
        .phone(userEntity.getPhone())
        .address(userEntity.getAddress())
        .build();
  }
}
