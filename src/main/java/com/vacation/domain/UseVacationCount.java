package com.vacation.domain;

import lombok.Getter;

@Getter
public class UseVacationCount {

    private float useVacationCount;

    private UseVacationCount(float useVacationCount) {
        this.useVacationCount = useVacationCount;
    }

    public static UseVacationCount of(float useVacationCount) {
        return new UseVacationCount(useVacationCount);
    }

    public void increaseUseVacationCount(float useVacationCount) {
        this.useVacationCount += useVacationCount;
    }

    public void decreaseUseVacationCount(float useVacationCount) {
        this.useVacationCount -= useVacationCount;
    }
}
