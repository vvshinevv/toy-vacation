package com.vacation.domain;

import lombok.Getter;

@Getter
public class VacationApplicationNo {
    private long vacationApplicationNo;

    private VacationApplicationNo(long vacationApplicationNo) {
        this.vacationApplicationNo = vacationApplicationNo;
    }

    public static VacationApplicationNo of(long vacationApplicationNo) {
        return new VacationApplicationNo(vacationApplicationNo);
    }
}
