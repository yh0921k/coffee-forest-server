package com.coffeeforest.domains.auth.presentation;

import com.coffeeforest.domains.auth.application.UserSignOutService;
import com.coffeeforest.domains.auth.application.dto.UserTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSignOutController {

  private final UserSignOutService userSignOutService;

  @DeleteMapping("/sign-out")
  public ResponseEntity signOut(@RequestBody UserTokenInfo userTokenInfo) {
    userSignOutService.signOut(userTokenInfo);
    return ResponseEntity.ok().build();
  }
}
