package com.coffeeforest.domains.user_profile.application;

import com.coffeeforest.commons.aws.S3FileUploader;
import com.coffeeforest.domains.user.application.UserFindService;
import com.coffeeforest.domains.user.domain.UserEntity;
import com.coffeeforest.domains.user_profile.application.dto.ProfileImageUpdateRequest;
import com.coffeeforest.domains.user_profile.application.dto.ProfileImageUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileUpdateService {

  private final UserFindService userFindService;
  private final S3FileUploader s3FileUploader;

  @Transactional
  public ProfileImageUpdateResponse update(
      ProfileImageUpdateRequest profileImageUpdateRequest, List<MultipartFile> profileImages) {
    UserEntity userEntity = userFindService.findById(profileImageUpdateRequest.getUserIndex());

    List<Long> deleteIndexList = profileImageUpdateRequest.getDeleteIndexList();
    if (deleteIndexList.size() > 0) {
      s3FileUploader.delete(profileImageUpdateRequest.getUserIndex(), deleteIndexList);
    }

    List<String> pathList = s3FileUploader.upload(userEntity.getId(), profileImages);

    if(pathList.size() > 0) {
      userEntity.profileImages(pathList.get(0));
    }

    return ProfileImageUpdateResponse.builder()
        .deleteIndexList(deleteIndexList)
        .uploadedPathList(pathList)
        .build();
  }
}
