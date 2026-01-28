package com.project.hems.simulator_service.web.exception;

public class MeterStatusAlreadyPresentException extends RuntimeException {

    public MeterStatusAlreadyPresentException(String msg) {
        super(msg);
    }
}
