package com.project.hems.SiteManagerService.controller;

import com.project.hems.SiteManagerService.dto.OwnerDto;
import com.project.hems.SiteManagerService.dto.SiteRequestDto;
import com.project.hems.SiteManagerService.dto.SiteResponseDto;
import com.project.hems.SiteManagerService.entity.Address;
import com.project.hems.SiteManagerService.entity.Owner;
import com.project.hems.SiteManagerService.entity.Site;
import com.project.hems.SiteManagerService.entity.Solar;
import com.project.hems.SiteManagerService.service.SiteService;
import com.project.hems.SiteManagerService.util.ValueMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/site")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Site APIs",description = "Read , Update and Delete Site")
public class SiteController {

    private final SiteService siteService;
    private final ValueMapper valueMapper;

    @PostMapping("/create-site")
    public ResponseEntity<Site> createSite(
            @RequestBody SiteRequestDto siteRequestDto,
            @AuthenticationPrincipal Jwt jwt
    ){
        log.info("Authenticated Object:- "+jwt);
        log.info("Subject:- "+jwt.getSubject());
        log.info("site is created successfully");
        String userSub=jwt.getSubject();
        String email = jwt.getClaim("https://hems.com/email");
        Site site=siteService.createSite(siteRequestDto,userSub);
        return new ResponseEntity<>(site,HttpStatus.CREATED);
    }
        @GetMapping("/fetch-site-by-id/{siteId}")
        public ResponseEntity<Site> getSite(@PathVariable UUID siteId){
            Site site=siteService.fetchSiteById(siteId);
            return new ResponseEntity<>(site,HttpStatus.OK);
        }

        @GetMapping("/fetch-all-site")
        public ResponseEntity<List<Site>> getAllSites(@AuthenticationPrincipal Jwt jwt){
            List<Site> sites=siteService.fetchAllSite();
            log.info("/fetch-all-site api call");
            System.out.println("token is"+jwt.getTokenValue());
            return new ResponseEntity<>(sites,HttpStatus.OK);
        }

//    @GetMapping("/fetch-all-site")
//    public CompletableFuture<ResponseEntity<List<SiteResponseDto>>> getAllSites() {
//        return siteService.fetchAllSites().thenApply(ResponseEntity::ok);
//    }


}
