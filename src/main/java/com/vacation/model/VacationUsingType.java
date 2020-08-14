package com.vacation.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum VacationUsingType {

    DAY_OFF("dayOff", 1.0f),
    HALF_DAY_OFF("halfDayOff", 0.5f),
    HALF_HALF_DAY_OFF("halfHalfDayOff", 0.25f),
    NONE("", 0);

    private String type;
    private float count;

    VacationUsingType(String type, float count) {
        this.type = type;
        this.count = count;
    }

    public float getCount() {
        return count;
    }

    public String getType() {
        return type;
    }

    public static VacationUsingType getType(String target) {
        return Arrays.stream(VacationUsingType.values())
                .filter(vacationUsingType -> StringUtils.equals(vacationUsingType.getType(), target))
                .findFirst()
                .orElse(NONE);
    }
}
