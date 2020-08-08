package com.vacation.domain;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class MemberName {

    private String name;

    private MemberName(String name) {
        this.name = name;
    }

    public static MemberName of(String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("이름 정보가 없습니다.");
        }

        return new MemberName(name);
    }
}
