package com.coffeeforest.domains.admin.application;

import com.coffeeforest.domains.admin.application.dto.AdminVerificationInfo;
import com.coffeeforest.domains.user.domain.Position;
import com.coffeeforest.domains.work.application.WorkFindService;
import com.coffeeforest.domains.work.domain.WorkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminVerificationService {

  private final WorkFindService workFindService;

  public void verify(AdminVerificationInfo adminVerificationInfo) {
    WorkEntity workEntity =
        workFindService.findByUserIndexAndCompanyIndex(
            adminVerificationInfo.getUserIndex(), adminVerificationInfo.getCompanyIndex());
    workEntity.getUserEntity().isValidPosition(Position.ADMIN);
  }
}
