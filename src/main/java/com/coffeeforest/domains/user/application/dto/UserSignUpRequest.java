package com.coffeeforest.domains.user.application.dto;

import com.coffeeforest.domains.company.application.dto.CompanySaveRequest;
import lombok.Getter;

@Getter
public class UserSignUpRequest {
  UserSaveRequest userSaveRequest;
  CompanySaveRequest companySaveRequest;
}
