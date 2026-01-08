package com.project.hems.auth_service_hems.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//this is only for testing login user data is correctly printed or not
@RestController
public class DataController {
    @GetMapping("/checkeddata")
    public Map<String, Object> checkedData(
            @AuthenticationPrincipal Jwt jwt) {

        return Map.of(
                "message", "Secure data access granted",
                "user", jwt.getClaimAsString("email"),
                "issuer", jwt.getIssuer().toString()
        );
    }
}
