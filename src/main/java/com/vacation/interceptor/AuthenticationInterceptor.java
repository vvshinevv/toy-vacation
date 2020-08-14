package com.vacation.interceptor;

import com.vacation.util.AES256Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Slf4j
public class AuthenticationInterceptor extends AbstractExcludedUriHandlerInterceptorAdapter implements InitializingBean {

    @Autowired
    private AES256Util aes256Util;

    @Override
    public void afterPropertiesSet() throws Exception {
        add("/api/login");
    }

    @Override
    public boolean doPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authCookieValue = Arrays.stream(request.getCookies())
                .filter(cookie -> StringUtils.equals(cookie.getName(), "v_login"))
                .map(Cookie::getValue)
                .distinct()
                .findFirst()
                .orElse(null);

        if (StringUtils.isBlank(authCookieValue)) {
            log.error("인증되지 않은 회원입니다.");
            throw new RuntimeException("인증되지 않은 회원입니다.");
        }

        String authValueDecryption;
        try {
            authValueDecryption = aes256Util.decrypt(authCookieValue);
        } catch (Exception e) {
            log.error("쿠키 복호화 실패 :: ", e);
            authValueDecryption = null;
        }

        if (StringUtils.isBlank(authValueDecryption)) {
            log.error("인증되지 않은 회원입니다.");
            throw new RuntimeException("인증되지 않은 회원입니다.");
        }

        String[] authValues = StringUtils.split(authValueDecryption, ",");

        long memberNo = Long.parseLong(authValues[0]);
        String memberName = authValues[1];

        request.setAttribute("memberNo", memberNo);
        request.setAttribute("memberName", memberName);

        return true;
    }

    @Override
    public void doPostHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void doAfterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void doAfterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    }
}
