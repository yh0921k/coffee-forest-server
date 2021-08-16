package com.coffeeforest.domains.work.presentation;

import com.coffeeforest.domains.work.application.WorkUpdateService;
import com.coffeeforest.domains.work.application.dto.WorkUpdateInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkUpdateController {

  private final WorkUpdateService workUpdateService;

  @PatchMapping("/works")
  public ResponseEntity<WorkUpdateInfo> update(@RequestBody WorkUpdateInfo workUpdateInfo) {
    WorkUpdateInfo response = workUpdateService.update(workUpdateInfo);
    return ResponseEntity.ok(response);
  }
}
