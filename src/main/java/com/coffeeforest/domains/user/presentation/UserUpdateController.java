package com.coffeeforest.domains.user.presentation;

import com.coffeeforest.domains.user.application.UserUpdateService;
import com.coffeeforest.domains.user.application.dto.UserUpdateInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserUpdateController {
  private final UserUpdateService userUpdateService;

  @PatchMapping("/users")
  public ResponseEntity<UserUpdateInfo> update(
      @RequestBody UserUpdateInfo userUpdateInfo) {
    UserUpdateInfo response = userUpdateService.update(userUpdateInfo);
    return ResponseEntity.ok(response);
  }
}
