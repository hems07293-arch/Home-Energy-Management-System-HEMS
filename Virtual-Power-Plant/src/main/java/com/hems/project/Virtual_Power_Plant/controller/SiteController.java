package com.hems.project.Virtual_Power_Plant.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.hems.project.Virtual_Power_Plant.service.SiteCreationService;
import com.hems.project.hems_api_contracts.contract.site.OwnerDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/vpp")
@RestController
public class SiteController {
    private final SiteCreationService service;
    
    // @GetMapping("/fetch-all-site")
    // public ResponseEntity<List<Object>> fetchAllSite(){
    //     return service.fetchAllSites();
    // }

    @GetMapping("/fetch-all-owner")
    public ResponseEntity<List<OwnerDto>> fetchAllSite(){
        return service.fetchAllOnwer();
    }

    @GetMapping("/check")
    public String getMethodName() {
        return "checking done";
    }

     @GetMapping("/check-token")
    public String checkToken( @AuthenticationPrincipal Jwt jwt) {
        return jwt.toString();
    }
    
    
}
