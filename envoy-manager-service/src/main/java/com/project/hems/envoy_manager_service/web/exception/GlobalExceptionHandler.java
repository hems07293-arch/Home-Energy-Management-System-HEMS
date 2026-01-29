package com.project.hems.envoy_manager_service.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MeterStatusNotFoudException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CustomizedErrorResponse handleMeterStatusNotFoudException(MeterStatusNotFoudException ex) {
        return CustomizedErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error("METER_NOT_FOUND")
                .message(ex.getMessage())
                .build();
    }
}
