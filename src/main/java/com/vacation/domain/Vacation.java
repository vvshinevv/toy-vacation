package com.vacation.domain;

import com.vacation.constant.VacationConstant;
import com.vacation.dto.VacationDTO;
import com.vacation.model.VacationType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
public class Vacation {

    private VacationNo vacationNo;
    private TotalVacationCount totalVacationCount;
    private LeftVacationCount leftVacationCount;
    private UseVacationCount useVacationCount;
    private VacationType vacationType;
    private LocalDateTime assignVacationDate;
    private LocalDateTime expireVacationDate;

    private Vacation() {
        this.vacationNo = VacationNo.of(0);
        this.totalVacationCount = TotalVacationCount.of(0.0f);
        this.leftVacationCount = LeftVacationCount.of(0.0f);
        this.useVacationCount = UseVacationCount.of(0.0f);
        this.vacationType = VacationType.NONE;
    }

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

    private Vacation(
            VacationNo vacationNo,
            TotalVacationCount totalVacationCount,
            LeftVacationCount leftVacationCount,
            UseVacationCount useVacationCount,
            VacationType vacationType,
            LocalDateTime assignVacationDate,
            LocalDateTime expireVacationDate) {

        this.vacationNo = vacationNo;
        this.totalVacationCount = totalVacationCount;
        this.leftVacationCount = leftVacationCount;
        this.useVacationCount = useVacationCount;
        this.vacationType = vacationType;
        this.assignVacationDate = assignVacationDate;
        this.expireVacationDate = expireVacationDate;
    }

    public static Vacation of(VacationDTO vacationDTO) {

        if (Objects.isNull(vacationDTO)) {
            return new Vacation();
        }

        long vacationNo = vacationDTO.getVacationNo();
        float totalVacationCount = vacationDTO.getTotalVacationCount();
        float leftVacationCount = vacationDTO.getLeftVacationCount();
        float userVacationCount = vacationDTO.getUseVacationCount();
        VacationType vacationType = vacationDTO.getVacationType();
        LocalDateTime assignVacationDate = vacationDTO.getAssignVacationDate();
        LocalDateTime expireVacationDate = vacationDTO.getExpireVacationDate();

        return new Vacation(
                VacationNo.of(vacationNo),
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
        LocalDateTime expireVacationDate = ZonedDateTime.of(assignVacationDate.toLocalDate().plusYears(1L), LocalTime.MAX, ZoneId.systemDefault()).toLocalDateTime();

        return new Vacation(
                TotalVacationCount.of(totalVacationCount),
                LeftVacationCount.of(leftVacationCount),
                UseVacationCount.of(userVacationCount),
                VacationType.ANNUAL,
                assignVacationDate,
                expireVacationDate
        );
    }

    public boolean haveAvailableVacation(float useVacationCount) {
        return leftVacationCount.compareTo(useVacationCount) > 0;
    }

    public void useVacation(float useVacationCount) {
        decreaseLeftVacationCount(useVacationCount);
        increaseUseVacationCount(useVacationCount);
    }

    public void cancelVacation(float useVacationCount) {
        increaseLeftVacationCount(useVacationCount);
        decreaseUseVacationCount(useVacationCount);
    }

    private void decreaseLeftVacationCount(float useVacationCount) {
        this.leftVacationCount.decreaseLeftVacationCount(useVacationCount);
    }

    private void increaseLeftVacationCount(float useVacationCount) {
        this.leftVacationCount.increaseLeftVacationCount(useVacationCount);
    }

    private void increaseUseVacationCount(float useVacationCount) {
        this.useVacationCount.increaseUseVacationCount(useVacationCount);
    }

    private void decreaseUseVacationCount(float useVacationCount) {
        this.useVacationCount.decreaseUseVacationCount(useVacationCount);
    }
}
