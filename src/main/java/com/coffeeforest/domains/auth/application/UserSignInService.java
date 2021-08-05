package com.coffeeforest.domains.auth.application;

import com.coffeeforest.commons.jwt.AuthToken;
import com.coffeeforest.domains.auth.application.dto.UserSignInRequest;
import com.coffeeforest.domains.auth.application.dto.UserSignInResponse;
import com.coffeeforest.domains.auth.application.dto.UserTokenInfo;
import com.coffeeforest.domains.company.domain.CompanyEntity;
import com.coffeeforest.domains.company_applicant.application.CompanyApplicantFindService;
import com.coffeeforest.domains.company_applicant.domain.CompanyApplicantStatus;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.work.application.WorkFindService;
import com.coffeeforest.domains.work.domain.WorkStatus;
import com.coffeeforest.domains.work_applicant.application.WorkApplicantFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSignInService {
  private final UserFindService userFindService;
  private final UserAuthService userAuthService;
  private final PasswordService passwordService;
  private final WorkApplicantFindService workApplicantFindService;
  private final CompanyApplicantFindService companyApplicantFindService;
  private final WorkFindService workFindService;

  @Transactional
  public UserSignInResponse signIn(
      UserSignInRequest userSignInRequest, HttpServletResponse httpServletResponse) {
    UserEntity userEntity = userFindService.findByEmail(userSignInRequest.getEmail());
    passwordService.matches(userEntity.getPassword(), userSignInRequest.getPassword());

    AuthToken accessToken = userAuthService.createAccessToken(userEntity);
    AuthToken refreshToken = userAuthService.createRefreshToken(userEntity);
    userAuthService.save(userEntity, refreshToken.getValue());

    Cookie cookie = new Cookie("refreshToken", refreshToken.getValue());
    cookie.setHttpOnly(true);
    // cookie.setSecure(true);
    cookie.setMaxAge(60 * 2);
    httpServletResponse.addCookie(cookie);

    WorkStatus workStatus =
        workApplicantFindService.existsByWorkStatus(userEntity.getId(), WorkStatus.WAITING);

    CompanyApplicantStatus companyApplicantStatus =
        companyApplicantFindService.existsByCompanyApplicantStatus(
            userEntity.getId(), CompanyApplicantStatus.WAIT);

    Optional<CompanyEntity> companyEntity =
        workFindService
            .findByUserIndexAndWorkStatus(userEntity.getId(), WorkStatus.WORKING)
            .map(entity -> entity.getCompanyEntity());

    return UserSignInResponse.builder()
        .userIndex(userEntity.getId())
        .email(userEntity.getEmail())
        .name(userEntity.getName())
        .position(userEntity.getPosition())
        .profileImage(userEntity.getProfileImage())
        .userTokenInfo(
            UserTokenInfo.builder()
                .userIndex(userEntity.getId())
                .accessToken(accessToken.getValue())
                .refreshToken(refreshToken.getValue())
                .build())
        .companyIndex(companyEntity.isPresent() ? companyEntity.get().getId() : 0)
        .workApplicantStatus(workStatus)
        .companyApplicantStatus(companyApplicantStatus)
        .build();
  }
}
