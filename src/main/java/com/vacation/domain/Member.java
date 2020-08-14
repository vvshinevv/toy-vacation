package com.vacation.domain;

import com.vacation.dto.MemberDTO;
import com.vacation.dto.VacationDTO;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Member {

    private MemberNo memberNo;
    private MemberName memberName;
    private Vacations vacations;

    private Member(MemberNo memberNo, MemberName memberName, Vacations vacations) {
        this.memberNo = memberNo;
        this.memberName = memberName;
        this.vacations = vacations;
    }

    public static Member of(MemberNo memberNo, MemberName memberName) {
        return new Member(memberNo, memberName, null);
    }

    public static Member of(MemberDTO memberDTO, List<VacationDTO> vacationDTOs) {
        long no = memberDTO.getMemberNo();
        String name = memberDTO.getName();

        MemberNo memberNo = MemberNo.of(no);
        MemberName memberName = MemberName.of(name);
        Vacations vacations = Vacations.of(vacationDTOs);

        return new Member(memberNo, memberName, vacations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return Objects.equals(memberNo, member.memberNo) &&
                Objects.equals(memberName, member.memberName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, memberName);
    }
}
