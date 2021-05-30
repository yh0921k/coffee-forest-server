package com.coffeeforest.domains.user.application;

import com.coffeeforest.domains.company.application.CompanySaveService;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.user.application.dto.UserSaveRequest;
import com.coffeeforest.domains.user.application.dto.UserSignUpRequest;
import com.coffeeforest.domains.user.application.dto.UserSignUpResponse;
import com.coffeeforest.domains.user.domain.Position;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.user.domain.UserRepository;
import com.coffeeforest.domains.work.application.WorkSaveService;
import com.coffeeforest.domains.work.application.dto.WorkSaveRequest;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

  private final UserRepository userRepository;
  private final CompanySaveService companySaveService;
  private final WorkSaveService workSaveService;

  @Transactional
  public ResponseEntity<UserSignUpResponse> signUp(UserSignUpRequest userSignUpRequest) {
    UserEntity userEntity = save(userSignUpRequest.getUserSaveRequest());

    if (userEntity.getPosition() == Position.ADMIN) {
      CompanyEntity companyEntity =
          companySaveService.save(userSignUpRequest.getCompanySaveRequest(), userEntity);

      workSaveService.save(
          WorkSaveRequest.builder().companyEntity(companyEntity).userEntity(userEntity).build());
    }

    return ResponseEntity.ok(UserSignUpResponse.builder().userIndex(userEntity.getId()).build());
  }

  @Transactional
  public UserEntity save(UserSaveRequest userSaveRequest) {
    return userRepository.save(
        UserEntity.builder()
            .email(userSaveRequest.getEmail())
            .password(userSaveRequest.getPassword())
            .position(userSaveRequest.getPosition())
            .name(userSaveRequest.getName())
            .phone(userSaveRequest.getPhone())
            .address(userSaveRequest.getAddress())
            .build());
  }

  public ResponseEntity<String> isDuplicated(String email) {
    String result = userRepository.existsByEmail(email) ? "Duplicated" : "Available";
    return ResponseEntity.ok(result);
  }
}
