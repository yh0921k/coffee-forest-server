package com.coffeeforest.domains.user.presentation;

import com.coffeeforest.domains.user.application.UserSignUpService;
import com.coffeeforest.domains.user.application.dto.UserSignUpRequest;
import com.coffeeforest.domains.user.application.dto.UserSignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserSignUpController {

  private final UserSignUpService userSignUpService;

  @PostMapping("/users")
  public ResponseEntity<UserSignUpResponse> signUp(
      @RequestBody UserSignUpRequest userSignUpRequest) {
    return ResponseEntity.ok(userSignUpService.signUp(userSignUpRequest));
  }

  @GetMapping("/users/duplication")
  public ResponseEntity<String> isDuplicated(@RequestParam String email) {
    return ResponseEntity.ok(userSignUpService.isDuplicated(email));
  }
}
