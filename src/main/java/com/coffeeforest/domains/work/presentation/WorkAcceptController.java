package com.coffeeforest.domains.work.presentation;

import com.coffeeforest.domains.work.application.WorkSaveService;
import com.coffeeforest.domains.work.application.dto.WorkSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkAcceptController {

  private final WorkSaveService workSaveService;

  @PostMapping("/work")
  public ResponseEntity accept(@RequestBody WorkSaveRequest workSaveRequest) {
    workSaveService.accept(workSaveRequest);
    return ResponseEntity.ok().build();
  }
}
