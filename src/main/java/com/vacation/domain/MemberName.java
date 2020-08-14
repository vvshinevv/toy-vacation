package com.vacation.domain;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberName that = (MemberName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
