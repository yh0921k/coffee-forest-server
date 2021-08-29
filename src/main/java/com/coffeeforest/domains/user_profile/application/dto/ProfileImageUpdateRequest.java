package com.coffeeforest.domains.user_profile.application.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ProfileImageUpdateRequest {
  private Long userIndex;
  private List<Long> deleteIndexList;
}
