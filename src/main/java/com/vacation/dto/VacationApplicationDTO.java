package com.vacation.dto;

import java.time.LocalDateTime;

public class VacationApplicationDTO {

    private long vacationApplicationNo;
    private long memberNo;
    private long vacationNo;
    private LocalDateTime applicationDate;
    private LocalDateTime vacationStartDate;
    private LocalDateTime vacationEndDate;
    private String cancelYN;
    private LocalDateTime cancelDate;

    public long getVacationApplicationNo() {
        return vacationApplicationNo;
    }

    public void setVacationApplicationNo(long vacationApplicationNo) {
        this.vacationApplicationNo = vacationApplicationNo;
    }

    public long getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(long memberNo) {
        this.memberNo = memberNo;
    }

    public long getVacationNo() {
        return vacationNo;
    }

    public void setVacationNo(long vacationNo) {
        this.vacationNo = vacationNo;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDateTime getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(LocalDateTime vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public LocalDateTime getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(LocalDateTime vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }

    public String getCancelYN() {
        return cancelYN;
    }

    public void setCancelYN(String cancelYN) {
        this.cancelYN = cancelYN;
    }

    public LocalDateTime getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDateTime cancelDate) {
        this.cancelDate = cancelDate;
    }
}
