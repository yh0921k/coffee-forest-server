package com.coffeeforest.domains.user.application;

import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFindService {
  private final UserRepository userRepository;

  public UserEntity findByEmail(String email) {
    UserEntity userEntity =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));
    return userEntity;
  }

  public UserEntity findById(Long userIndex) {
    UserEntity userEntity =
        userRepository
            .findById(userIndex)
            .orElseThrow(() -> new IllegalArgumentException("Invalid User Index"));

    return userEntity;
  }
}
