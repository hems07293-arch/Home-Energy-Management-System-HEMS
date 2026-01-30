package com.project.hems.simulator_service.web.exception;

public class MeterAlreadyDispatchedException extends RuntimeException {
    public MeterAlreadyDispatchedException(String msg) {
        super(msg);
    }
}
