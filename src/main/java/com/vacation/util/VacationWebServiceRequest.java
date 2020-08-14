package com.vacation.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vacation.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class VacationWebServiceRequest {

    @Autowired
    private ObjectMapper mapper;

    public Map<String, String> getRequestBody(NativeWebRequest webRequest) {

        try {

            HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
            String requestBody = IOUtils.toString(servletRequest.getInputStream());
            webRequest.setAttribute("JSON_REQUEST_BODY", requestBody, NativeWebRequest.SCOPE_REQUEST);

            if (StringUtils.isBlank(requestBody)) {
                throw new ApiException("body 가 비어있습니다.", HttpStatus.BAD_REQUEST);
            }

            JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, String.class, String.class);
            return mapper.readValue(requestBody, javaType);

        } catch (IOException e) {
            log.error("IOException is occurred while getRequestBody method :: ", e);
            throw new ApiException(e);
        }
    }
}
