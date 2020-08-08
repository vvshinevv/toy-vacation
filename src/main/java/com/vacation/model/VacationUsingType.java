package com.vacation.model;

public enum VacationUsingType {

    DAY_OFF(1.0f),
    HALF_DAY_OFF(0.5f),
    HALF_HALF_DAY_OFF(0.25f);

    private float count;

    VacationUsingType(float count) {
        this.count = count;
    }

    public float getCount() {
        return count;
    }
}
