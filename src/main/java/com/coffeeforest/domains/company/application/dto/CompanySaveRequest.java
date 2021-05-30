package com.coffeeforest.domains.company.application.dto;

import lombok.*;

@Getter
public class CompanySaveRequest {
  private String name;
  private String address;
  private String businessNumber;
}