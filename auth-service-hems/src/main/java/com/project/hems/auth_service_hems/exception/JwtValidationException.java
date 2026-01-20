package com.project.hems.auth_service_hems.exception;

public class JwtValidationException extends RuntimeException {
  public JwtValidationException(String message) {
    super(message);
  }
}
