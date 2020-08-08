package com.vacation.domain;

import com.vacation.dto.MemberDTO;
import com.vacation.dto.VacationDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class Member {

    private MemberName memberName;
    private Vacations vacations;

    private Member(MemberName memberName, Vacations vacations) {
        this.memberName = memberName;
        this.vacations = vacations;
    }

    public static Member of(MemberDTO memberDTO, List<VacationDTO> vacationDTOs) {
        String name = memberDTO.getName();
        MemberName memberName = MemberName.of(name);
        Vacations vacations = Vacations.of(vacationDTOs);

        return new Member(memberName, vacations);
    }
}
