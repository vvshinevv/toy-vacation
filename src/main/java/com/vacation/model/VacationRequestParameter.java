package com.vacation.model;

public enum VacationRequestParameter {

    VACATION_NO("vacationNo"),
    START_VACATION_DATE("startVacationDate"),
    VACATION_USING_TYPE("vacationUsingType"),
    END_VACATION_DATE("endVacationDate"),
    COMMENT("comment");

    VacationRequestParameter(String parameterName) {
        this.parameterName = parameterName;
    }

    private String parameterName;

    public String getParameterName() {
        return parameterName;
    }
}
