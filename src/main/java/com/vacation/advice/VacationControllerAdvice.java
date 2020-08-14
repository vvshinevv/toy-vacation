package com.vacation.advice;

import com.vacation.annotation.VacationRestController;
import com.vacation.exception.ApiException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(annotations = VacationRestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class VacationControllerAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiException handleBindException(BindException bindException) {
        return new ApiException(makeErrorMessage(bindException), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ApiException handleRuntimeException(RuntimeException runtimeException) {
        return new ApiException(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private String makeErrorMessage(BindException exception) {
        StringBuilder builder = new StringBuilder();
        for (ObjectError error : exception.getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                builder.append(fieldError.getDefaultMessage());
            }
        }
        return builder.toString();
    }
}
