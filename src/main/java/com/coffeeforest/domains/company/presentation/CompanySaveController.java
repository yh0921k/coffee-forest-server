package com.coffeeforest.domains.company.presentation;

import com.coffeeforest.domains.company.application.CompanySaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanySaveController {

    private final CompanySaveService companySaveService;

    @GetMapping("/company/duplication")
    public ResponseEntity<String> isDuplicated(@RequestParam String businessNumber) {
        return companySaveService.isDuplicated(businessNumber);
    }
}
