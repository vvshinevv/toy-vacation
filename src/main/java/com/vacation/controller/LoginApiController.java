package com.vacation.controller;

import com.vacation.dto.request.LoginRequest;
import com.vacation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/api")
public class LoginApiController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean requestVacation(HttpServletResponse httpServletResponse, @RequestBody LoginRequest loginRequest) throws GeneralSecurityException {
        return loginService.login(httpServletResponse, loginRequest);
    }
}
