package com.coffeeforest.commons.converter;

import com.coffeeforest.domains.user_profile.application.dto.ProfileImageUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToUserUpdateRequestConverter implements Converter<String, ProfileImageUpdateRequest> {

  private final ObjectMapper objectMapper;

  @Override
  public ProfileImageUpdateRequest convert(String source) {
    try {
      return objectMapper.readValue(source, ProfileImageUpdateRequest.class);
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }
  }
}
