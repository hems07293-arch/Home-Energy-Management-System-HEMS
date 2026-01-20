package com.project.hems.SiteManagerService.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("check-token-is-present")
public class CheckToken {

    @GetMapping("/")
    public ResponseEntity<String> checkToken(@AuthenticationPrincipal Jwt jwt){
        String sub=jwt.getSubject();
        return ResponseEntity.ok(sub);
    }

}
