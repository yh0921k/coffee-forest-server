package com.coffeeforest.domains.user.application;

import com.coffeeforest.domains.company.application.CompanyFindService;
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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

  private final UserRepository userRepository;
  private final CompanySaveService companySaveService;
  private final CompanyFindService companyFindService;
  private final WorkSaveService workSaveService;

  @Transactional
  public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest) {
    UserEntity userEntity = save(userSignUpRequest.getUserSaveRequest());

    CompanyEntity companyEntity = null;
    if (userEntity.getPosition() == Position.ADMIN) {
      companyEntity =
          companySaveService.save(userSignUpRequest.getCompanySaveRequest(), userEntity);
    } else if (userEntity.getPosition() == Position.EMPLOYEE) {
      companyEntity =
          companyFindService.findByName(userSignUpRequest.getCompanySaveRequest().getName());
    }

    workSaveService.save(
        WorkSaveRequest.builder().companyEntity(companyEntity).userEntity(userEntity).build());

    return UserSignUpResponse.builder().userIndex(userEntity.getId()).build();
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

  public String isDuplicated(String email) {
    String result = userRepository.existsByEmail(email) ? "Duplicated" : "Available";
    return result;
  }
}
