package com.coffeeforest.domains.auth.application;

import com.coffeeforest.domains.auth.application.dto.UserTokenInfo;
import com.coffeeforest.domains.auth.domain.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignOutService {

  private final UserTokenRepository userTokenRepository;

  @Transactional
  public void signOut(UserTokenInfo userTokenInfo) {
    Long userIndex = userTokenInfo.getUserIndex();
    userTokenRepository.deleteByUserEntityId(userIndex);
  }
}
