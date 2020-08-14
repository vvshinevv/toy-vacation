package com.vacation.dto;

import com.vacation.domain.Member;
import com.vacation.dto.request.VacationRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VacationApplicationDTO {
    private long vacationApplicationNo;
    private long memberNo;
    private long vacationNo;
    private LocalDateTime applicationDate;
    private LocalDateTime vacationStartDate;
    private LocalDateTime vacationEndDate;
    private float useVacationCount;
    private String cancelYN;
    private LocalDateTime cancelDate;

    public static VacationApplicationDTO of(VacationRequest vacationRequest, Member member) {
        long vacationNo = vacationRequest.getVacationNo();
        long memberNo = member.getMemberNo().getMemberNo();
        LocalDateTime applicationDate = LocalDateTime.now();
        LocalDateTime vacationStartDate = vacationRequest.getStartVacationDate();
        LocalDateTime vacationEndDate = vacationRequest.getEndVacationDate();
        float useVacationCount = vacationRequest.getUseVacationCount();

        VacationApplicationDTO vacationApplicationDTO = new VacationApplicationDTO();
        vacationApplicationDTO.setVacationNo(vacationNo);
        vacationApplicationDTO.setMemberNo(memberNo);
        vacationApplicationDTO.setApplicationDate(applicationDate);
        vacationApplicationDTO.setVacationStartDate(vacationStartDate);
        vacationApplicationDTO.setVacationEndDate(vacationEndDate);
        vacationApplicationDTO.setUseVacationCount(useVacationCount);

        return vacationApplicationDTO;
    }
}
