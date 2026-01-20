package com.project.hems.api_gateway_hems.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/login-success")
    public String loginSuccess(@AuthenticationPrincipal OidcUser user) {
        return "Email: " + user.getClaimAsString("email") +
                ", Name: " + user.getClaimAsString("name") +
                ", Sub: " + user.getSubject();
    }

    @GetMapping("/error")
    public String loginError() {
        return "Error in login check network!!";
    }




}
