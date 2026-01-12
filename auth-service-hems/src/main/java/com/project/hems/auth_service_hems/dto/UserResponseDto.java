package com.project.hems.auth_service_hems.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID id;
    private String email;
}
