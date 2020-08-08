package com.vacation.domain;

import lombok.Getter;

@Getter
public class LeftVacationCount {

    private float leftVacationCount;

    private LeftVacationCount(float leftVacationCount) {
        this.leftVacationCount = leftVacationCount;
    }

    public static LeftVacationCount of(float leftVacationCount) {
        return new LeftVacationCount(leftVacationCount);
    }
}
