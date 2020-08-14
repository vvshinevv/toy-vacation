package com.vacation.domain;

import lombok.Getter;

@Getter
public class VacationNo {
    private long vacationNo;

    private VacationNo(long vacationNo) {
        this.vacationNo = vacationNo;
    }

    public static VacationNo of(long vacationNo) {
        return new VacationNo(vacationNo);
    }
}
