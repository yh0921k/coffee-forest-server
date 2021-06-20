package com.coffeeforest.domains.user.application.dto;

import com.coffeeforest.domains.user.domain.Position;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSignInResponse {
    private Long companyIndex;
    private Long userIndex;
    private int year;
    private int month;
    private int weekNumber;
    private int weekStartDate;
    private int weekEndDate;
    private String name;
    private Position position;

}
