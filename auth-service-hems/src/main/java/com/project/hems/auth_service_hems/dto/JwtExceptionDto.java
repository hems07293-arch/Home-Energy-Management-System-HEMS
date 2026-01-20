package com.project.hems.auth_service_hems.dto;

import org.springframework.http.HttpStatus;

public record JwtExceptionDto(String message,
                              HttpStatus status,
                              int statusCode ) {
}
