package com.vacation.service;

import com.vacation.dto.MemberDTO;
import com.vacation.dto.request.LoginRequest;
import com.vacation.repository.LoginRepository;
import com.vacation.util.AES256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.GeneralSecurityException;
import java.util.Objects;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private AES256Util aes256Util;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean login(HttpServletResponse httpServletResponse, LoginRequest loginRequest) throws GeneralSecurityException {

        MemberDTO loginMember = loginRepository.selectMember(loginRequest.getName());
        if (Objects.isNull(loginMember)) {
            return false;
        }

        String passwordFromDB = loginMember.getPassword();
        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), passwordFromDB)) {
            return false;
        }

        long memberNo = loginMember.getMemberNo();
        String name = loginMember.getName();

        String target = memberNo + "," + name;
        String encryptTarget;
        encryptTarget = aes256Util.encrypt(target);

        Cookie loginCookie = new Cookie("v_login", encryptTarget);
        httpServletResponse.addCookie(loginCookie);
        return true;
    }
}
