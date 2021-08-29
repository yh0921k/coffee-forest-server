package com.coffeeforest.domains.user_profile.application.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileImageUpdateResponse {
  List<Long> deleteIndexList;
  List<String> uploadedPathList;
}
