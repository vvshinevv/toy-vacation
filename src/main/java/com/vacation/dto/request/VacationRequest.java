package com.vacation.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VacationRequest {
    private long vacationNo;
    private LocalDateTime startVacationDate;
    private LocalDateTime endVacationDate;
    private float useVacationCount;
    private String comment;
}
