package com.vacation.dto;

import com.vacation.domain.Vacation;
import com.vacation.model.VacationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class VacationDTO {

    private long vacationNo;
    private long memberNo;
    private float totalVacationCount;
    private float leftVacationCount;
    private float useVacationCount;
    private VacationType vacationType;
    private LocalDateTime assignVacationDate;
    private LocalDateTime expireVacationDate;

    public static VacationDTO of(long memberNo, Vacation vacation) {
        float totalVacationCount = vacation.getTotalVacationCount().getTotalVacationCount();
        float leftVacationCount = vacation.getLeftVacationCount().getLeftVacationCount();
        float useVacationCount = vacation.getUseVacationCount().getUseVacationCount();
        VacationType vacationType = vacation.getVacationType();
        LocalDateTime assignVacationDate = vacation.getAssignVacationDate();
        LocalDateTime expireVacationDate = vacation.getExpireVacationDate();

        VacationDTO vacationDTO = new VacationDTO();
        if (Objects.nonNull(vacation.getVacationNo())) {
            vacationDTO.setVacationNo(vacation.getVacationNo().getVacationNo());
        }
        vacationDTO.setMemberNo(memberNo);
        vacationDTO.setTotalVacationCount(totalVacationCount);
        vacationDTO.setLeftVacationCount(leftVacationCount);
        vacationDTO.setUseVacationCount(useVacationCount);
        vacationDTO.setVacationType(vacationType);
        vacationDTO.setAssignVacationDate(assignVacationDate);
        vacationDTO.setExpireVacationDate(expireVacationDate);

        return vacationDTO;
    }

    public static VacationDTO of(long memberNo, long vacationNo, Vacation vacation) {
        float totalVacationCount = vacation.getTotalVacationCount().getTotalVacationCount();
        float leftVacationCount = vacation.getLeftVacationCount().getLeftVacationCount();
        float useVacationCount = vacation.getUseVacationCount().getUseVacationCount();
        VacationType vacationType = vacation.getVacationType();
        LocalDateTime assignVacationDate = vacation.getAssignVacationDate();
        LocalDateTime expireVacationDate = vacation.getExpireVacationDate();

        VacationDTO vacationDTO = new VacationDTO();
        vacationDTO.setVacationNo(vacationNo);
        vacationDTO.setMemberNo(memberNo);
        vacationDTO.setTotalVacationCount(totalVacationCount);
        vacationDTO.setLeftVacationCount(leftVacationCount);
        vacationDTO.setUseVacationCount(useVacationCount);
        vacationDTO.setVacationType(vacationType);
        vacationDTO.setAssignVacationDate(assignVacationDate);
        vacationDTO.setExpireVacationDate(expireVacationDate);

        return vacationDTO;
    }
}
