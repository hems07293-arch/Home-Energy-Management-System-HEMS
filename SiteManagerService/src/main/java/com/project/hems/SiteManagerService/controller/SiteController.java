package com.project.hems.SiteManagerService.controller;

import com.project.hems.SiteManagerService.dto.OwnerDto;
import com.project.hems.SiteManagerService.dto.SiteRequestDto;
import com.project.hems.SiteManagerService.dto.SiteResponseDto;
import com.project.hems.SiteManagerService.entity.Address;
import com.project.hems.SiteManagerService.entity.Owner;
import com.project.hems.SiteManagerService.entity.Site;
import com.project.hems.SiteManagerService.service.SiteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/site")
@RequiredArgsConstructor
@Slf4j
public class SiteController {

    private final SiteService siteService;

    @PostMapping("/create-site")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Site> createSite(@RequestBody SiteRequestDto siteRequestDto){
        Site site=siteService.createSite(siteRequestDto);
        log.info("site is created successfully");
        return ResponseEntity.ok(site);
    }


    @GetMapping("/fetch-all-site")
    public ResponseEntity<List<SiteResponseDto>> getAllSites(){
        return null;
    }

}
