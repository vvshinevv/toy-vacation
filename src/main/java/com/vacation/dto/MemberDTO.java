package com.vacation.dto;

import com.vacation.domain.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberDTO {
    private long memberNo;
    private String name;
    private String password;
    private List<VacationDTO> vacationDTOs;

    public static MemberDTO of(Member member) {
        long memberNo = member.getMemberNo().getMemberNo();
        String name = member.getMemberName().getName();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberNo(memberNo);
        memberDTO.setName(name);

        return memberDTO;
    }
}
