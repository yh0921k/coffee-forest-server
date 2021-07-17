package com.coffeeforest.domains.user.presentation;

import com.coffeeforest.domains.user.application.UserSignUpService;
import com.coffeeforest.domains.user.application.dto.UserSaveRequest;
import com.coffeeforest.domains.user.application.dto.UserSignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserSignUpController {

  private final UserSignUpService userSignUpService;

  @PostMapping("/sign-up")
  public ResponseEntity<UserSignUpResponse> signUp(@RequestBody UserSaveRequest userSaveRequest) {
    userSignUpService.signUp(userSaveRequest);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/users/email")
  public ResponseEntity<String> isDuplicated(@RequestParam String email) {
    return ResponseEntity.ok(userSignUpService.isDuplicated(email));
  }
}
