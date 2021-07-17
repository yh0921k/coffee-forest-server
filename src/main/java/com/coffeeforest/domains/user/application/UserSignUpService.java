package com.coffeeforest.domains.user.application;

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

  @Transactional
  public void signUp(UserSaveRequest userSaveRequest) {
    userRepository.save(
        UserEntity.builder()
            .email(userSaveRequest.getEmail())
            .password(userSaveRequest.getPassword())
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
