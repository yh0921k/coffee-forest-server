package com.coffeeforest.domains.user_profile.presentation;

import com.coffeeforest.domains.user_profile.application.ProfileUpdateService;
import com.coffeeforest.domains.user_profile.application.dto.ProfileImageUpdateRequest;
import com.coffeeforest.domains.user_profile.application.dto.ProfileImageUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileUpdateController {

  private final ProfileUpdateService profileUpdateService;

  @PutMapping("/users")
  public ResponseEntity<ProfileImageUpdateResponse> update(
      @RequestParam ProfileImageUpdateRequest profileImageUpdateRequest,
      List<MultipartFile> profileImages) {
    ProfileImageUpdateResponse response =
        profileUpdateService.update(profileImageUpdateRequest, profileImages);
    return ResponseEntity.ok(response);
  }
}
