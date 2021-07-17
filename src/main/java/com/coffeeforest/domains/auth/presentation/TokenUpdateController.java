package com.coffeeforest.domains.auth.presentation;

import com.coffeeforest.domains.auth.application.TokenUpdateService;
import com.coffeeforest.domains.auth.application.dto.UserTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenUpdateController {

  private final TokenUpdateService tokenUpdateService;

  @PatchMapping("/refresh")
  public ResponseEntity<UserTokenInfo> refresh(@RequestBody UserTokenInfo userTokenInfo) {
    return ResponseEntity.ok(tokenUpdateService.refresh(userTokenInfo));
  }
}
