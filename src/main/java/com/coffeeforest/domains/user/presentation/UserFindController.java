package com.coffeeforest.domains.user.presentation;

import com.coffeeforest.domains.admin.application.dto.CompanyUserIndexPairRequest;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.application.dto.SimpleUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserFindController {

  private final UserFindService userFindService;

  @PostMapping("/users")
  public ResponseEntity<List<SimpleUserInfo>> findAll(
      @RequestBody CompanyUserIndexPairRequest request) {
    List<SimpleUserInfo> simpleUserInfoList = userFindService.findAll(request);
    return ResponseEntity.ok(simpleUserInfoList);
  }
}
