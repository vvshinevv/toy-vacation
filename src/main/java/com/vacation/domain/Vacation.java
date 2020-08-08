package com.vacation.domain;

import com.vacation.constant.VacationConstant;
import com.vacation.dto.VacationDTO;
import com.vacation.model.VacationType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Vacation {

    private TotalVacationCount totalVacationCount;
    private LeftVacationCount leftVacationCount;
    private UseVacationCount useVacationCount;
    private VacationType vacationType;
    private LocalDateTime assignVacationDate;
    private LocalDateTime expireVacationDate;

    private Vacation(
            TotalVacationCount totalVacationCount,
            LeftVacationCount leftVacationCount,
            UseVacationCount useVacationCount,
            VacationType vacationType,
            LocalDateTime assignVacationDate,
            LocalDateTime expireVacationDate) {

        this.totalVacationCount = totalVacationCount;
        this.leftVacationCount = leftVacationCount;
        this.useVacationCount = useVacationCount;
        this.vacationType = vacationType;
        this.assignVacationDate = assignVacationDate;
        this.expireVacationDate = expireVacationDate;
    }

    public static Vacation of(VacationDTO vacationDTO) {
        float totalVacationCount = vacationDTO.getTotalVacationCount();
        float leftVacationCount = vacationDTO.getLeftVacationCount();
        float userVacationCount = vacationDTO.getUseVacationCount();
        VacationType vacationType = vacationDTO.getVacationType();
        LocalDateTime assignVacationDate = vacationDTO.getAssignVacationDate();
        LocalDateTime expireVacationDate = vacationDTO.getExpireVacationDate();

        return new Vacation(
                TotalVacationCount.of(totalVacationCount),
                LeftVacationCount.of(leftVacationCount),
                UseVacationCount.of(userVacationCount),
                vacationType,
                assignVacationDate,
                expireVacationDate
        );
    }

    public static Vacation annualVacation() {
        float totalVacationCount = VacationConstant.ANNUAL_VACATION_COUNT;
        float leftVacationCount = VacationConstant.ANNUAL_VACATION_COUNT;
        float userVacationCount = 0.0f;
        LocalDateTime assignVacationDate = LocalDateTime.now();
        LocalDateTime expireVacationDate = assignVacationDate.plusYears(1L);

        return new Vacation(
                TotalVacationCount.of(totalVacationCount),
                LeftVacationCount.of(leftVacationCount),
                UseVacationCount.of(userVacationCount),
                VacationType.ANNUAL,
                assignVacationDate,
                expireVacationDate
        );
    }
}
