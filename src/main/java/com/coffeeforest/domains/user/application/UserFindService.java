package com.coffeeforest.domains.user.application;

import com.coffeeforest.commons.exception.ExceptionState;
import com.coffeeforest.commons.exception.detail.InvalidArgumentException;
import com.coffeeforest.domains.admin.application.dto.CompanyUserIndexPairRequest;
import com.coffeeforest.domains.user.application.dto.SimpleUserInfo;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.user.domain.UserRepository;
import com.coffeeforest.domains.work.application.WorkFindService;
import com.coffeeforest.domains.work.domain.WorkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserFindService {
  private final UserRepository userRepository;
  private final WorkFindService workFindService;

  public UserEntity findByEmail(String email) {
    UserEntity userEntity =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () ->
                    new InvalidArgumentException(
                        ExceptionState.INVALID_ARGUMENT, "Invalid Email or Password"));
    return userEntity;
  }

  public UserEntity findById(Long userIndex) {
    UserEntity userEntity =
        userRepository
            .findById(userIndex)
            .orElseThrow(
                () ->
                    new InvalidArgumentException(
                        ExceptionState.INVALID_ARGUMENT, "Invalid User Index"));

    return userEntity;
  }

  public List<SimpleUserInfo> findAll(CompanyUserIndexPairRequest request) {
    List<WorkEntity> workEntityList =
        workFindService.findAllByCompanyIndex(request.getCompanyIndex());
    List<SimpleUserInfo> simpleUserInfoList =
        workEntityList.stream()
            .map(
                workEntity -> {
                  UserEntity userEntity = workEntity.getUserEntity();
                  return SimpleUserInfo.builder()
                      .userIndex(userEntity.getId())
                      .name(userEntity.getName())
                      .email(userEntity.getEmail())
                      .profileImage(userEntity.getProfileImage())
                      .workStartTime(workEntity.getWorkStartTime())
                      .workEndTime(workEntity.getWorkEndTime())
                      .fullDayOffCount(workEntity.getFullDayOffCount())
                      .build();
                })
            .collect(Collectors.toList());

    return simpleUserInfoList;
  }
}
