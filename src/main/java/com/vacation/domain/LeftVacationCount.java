package com.vacation.domain;

import lombok.Getter;

@Getter
public class LeftVacationCount implements Comparable<Float> {

    private float leftVacationCount;

    private LeftVacationCount(float leftVacationCount) {
        this.leftVacationCount = leftVacationCount;
    }

    public static LeftVacationCount of(float leftVacationCount) {
        return new LeftVacationCount(leftVacationCount);
    }

    public void decreaseLeftVacationCount(float useVacationCount) {
        leftVacationCount -= useVacationCount;
    }

    public void increaseLeftVacationCount(float useVacationCount) {
        leftVacationCount += useVacationCount;
    }

    @Override
    public int compareTo(Float o) {
        return Float.compare(leftVacationCount, o);
    }
}
