package com.vacation.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class MemberNo {
    private long memberNo;

    private MemberNo(long memberNo) {
        this.memberNo = memberNo;
    }

    public static MemberNo of(long memberNo) {
        return new MemberNo(memberNo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberNo memberNo1 = (MemberNo) o;
        return memberNo == memberNo1.memberNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo);
    }
}
