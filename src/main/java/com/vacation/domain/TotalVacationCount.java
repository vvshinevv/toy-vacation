package com.vacation.domain;

import lombok.Getter;

@Getter
public class TotalVacationCount {

    private float totalVacationCount;

    private TotalVacationCount(float totalVacationCount) {
        this.totalVacationCount = totalVacationCount;
    }

    public static TotalVacationCount of(float totalVacationCount) {
        return new TotalVacationCount(totalVacationCount);
    }

    public void addToTotalVacationCount(float vacationCount) {
        this.totalVacationCount += vacationCount;
    }
}
