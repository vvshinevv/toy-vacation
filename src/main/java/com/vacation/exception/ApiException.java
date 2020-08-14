package com.vacation.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ApiException extends RuntimeException {

    private HttpStatus httpStatus;

    public ApiException(Throwable throwable) {
        super(throwable);
        super.setStackTrace(new StackTraceElement[]{});
    }

    public ApiException(String message, HttpStatus status) {
        super(message);
        super.setStackTrace(new StackTraceElement[]{});
        setHttpStatus(status);
    }
}
