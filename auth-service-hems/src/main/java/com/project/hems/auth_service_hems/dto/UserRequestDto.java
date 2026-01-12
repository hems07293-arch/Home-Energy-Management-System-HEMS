package com.project.hems.auth_service_hems.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;
}
