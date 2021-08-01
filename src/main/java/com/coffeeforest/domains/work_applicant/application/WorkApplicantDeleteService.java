package com.coffeeforest.domains.work_applicant.application;

import com.coffeeforest.domains.work_applicant.domain.WorkApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class WorkApplicantDeleteService {
  private final WorkApplicantRepository workApplicantRepository;

  @Transactional
  public void deleteByIndex(Long workApplicantIndex) {
    workApplicantRepository.deleteById(workApplicantIndex);
  }
}
