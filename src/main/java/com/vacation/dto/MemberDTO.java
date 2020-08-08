package com.vacation.dto;

import java.util.List;

public class MemberDTO {

    private long memberNo;
    private String name;
    private String password;
    private List<VacationDTO> vacationDTOs;

    public long getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(long memberNo) {
        this.memberNo = memberNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<VacationDTO> getVacationDTOs() {
        return vacationDTOs;
    }

    public void setVacationDTOs(List<VacationDTO> vacationDTOs) {
        this.vacationDTOs = vacationDTOs;
    }
}
