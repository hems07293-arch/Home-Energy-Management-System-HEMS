package com.project.hems.SiteManagerService.dto;

import org.springframework.http.HttpStatus;

public record ExceptionDto(
        String message,
        HttpStatus status,
        int statusCode
) {}
