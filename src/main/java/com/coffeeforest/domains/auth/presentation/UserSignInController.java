package com.coffeeforest.domains.auth.presentation;

import com.coffeeforest.domains.auth.application.UserSignInService;
import com.coffeeforest.domains.auth.application.dto.UserSignInRequest;
import com.coffeeforest.domains.auth.application.dto.UserSignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSignInController {
  private final UserSignInService userSignInService;

  @PostMapping("/sign-in")
  public ResponseEntity<UserSignInResponse> signIn(
      @RequestBody UserSignInRequest userSignInRequest) {
    return ResponseEntity.ok(userSignInService.signIn(userSignInRequest));
  }
}
