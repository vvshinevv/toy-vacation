package com.vacation.resolver;

import com.vacation.domain.Member;
import com.vacation.domain.MemberName;
import com.vacation.domain.MemberNo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Member.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        long no = (long) webRequest.getAttribute("memberNo", NativeWebRequest.SCOPE_REQUEST);
        String name = (String) webRequest.getAttribute("memberName", NativeWebRequest.SCOPE_REQUEST);

        MemberNo memberNo = MemberNo.of(no);
        MemberName memberName = MemberName.of(name);

        return Member.of(memberNo, memberName);
    }
}
