package com.project.hems.auth_service_hems.controller;


import com.project.hems.auth_service_hems.GridDataGenerated.Site;
import com.project.hems.auth_service_hems.GridDataGenerated.SiteService;
import com.project.hems.auth_service_hems.model.User;
import com.project.hems.auth_service_hems.repository.UserRepo;
import com.project.hems.auth_service_hems.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Random;

@Controller
public class HomeController {

    private final OAuth2AuthorizedClientService clientService;
    private final UserService userService;
    private final RestTemplate restTemplate;
    private final SiteService siteService;
    private final UserRepo userRepo;

    public HomeController(OAuth2AuthorizedClientService clientService,
                          UserService userService,
                          RestTemplate restTemplate, SiteService siteService, UserRepo userRepo) {
        this.clientService = clientService;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.siteService = siteService;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String home(Model model,
                       @AuthenticationPrincipal OidcUser oidcUser) {

        if (oidcUser == null) {
            return "index";
        }

        // Auth0 subject (e.g. google-oauth2|115714066732531125008)
        String subject = oidcUser.getSubject();
//        String email = oidcUser.getEmail();

        System.out.println("AUTH0 SUBJECT = " + subject);
//        System.out.println("EMAIL = " + email);

        model.addAttribute("userId", subject);
        model.addAttribute("profile", oidcUser.getClaims());

        // Load OAuth2 client
        OAuth2AuthorizedClient client =
                clientService.loadAuthorizedClient(
                        "auth0",
                        oidcUser.getName()
                );

        if (client != null) {
            System.out.println("ACCESS TOKEN = " +
                    client.getAccessToken().getTokenValue());

            if (client.getRefreshToken() != null) {
                System.out.println("REFRESH TOKEN = " +
                        client.getRefreshToken().getTokenValue());
            }

            System.out.println("Current time = " + LocalDateTime.now());
        }

        //split provider and provider user id safely
        String[] parts = subject.split("\\|");
        String provider = parts[0];          // google-oauth2
        String providerUserId = parts[1];    // 115714066732531125008
        String email= oidcUser.getEmail();

        Random random=new Random();
        //check user ek varr avi gayelo che using email
        if(userRepo.existsByEmail(email)){
            //find that exist user id
            //and assign to
            long user_id=userRepo.findUserIdByEmail(email).orElseThrow();

            User user = User.builder()
                    .provider(provider)
                    .providerUserId(providerUserId)
                    .providerSub(subject)
                    .userId(user_id)
                    .email(email)
                    .time(LocalDateTime.now())
                    .build();

            //have same user vare vare login kare toh database ma entry na padvi joiee only update thavu joiee time stamp


            userService.save(user);

            System.out.println("User stored successfully");

        }
        else{
            long user_id=random.nextLong();
            //bound to 4 digit
            long bound_id=Math.abs(user_id%10000);



            User user = User.builder()
                    .provider(provider)
                    .providerUserId(providerUserId)
                    .providerSub(subject)
                    .userId(bound_id)
                    .email(email)
                    .time(LocalDateTime.now())
                    .build();

            userService.save(user);

            System.out.println("User stored successfully");
        }


        //now user new ayvo hase game te method thi pan toh have e
        //first we check e use pehle thi exiest kare che ke nai using their email
        //if grid table ma e user exist nai karto hoy then and then we make new entry otherwise email hase toh nai karsu
        //1.check email in grid table
        boolean check_email_is_present=siteService.findEmail(email);
        if(check_email_is_present){
            System.out.println("already one entry user is present in grid table");
        }else{
            System.out.println("make one entry for user");
            System.out.println("1");
            Long user_id=userRepo.findUserIdByEmail(email).orElseThrow();
            System.out.println("2");
            Site site=Site.builder()
                    .email(email)
                    .site_data("""
                    {
                      "rows": 5,
                      "columns": 5,
                      "cells": [[1,0,1],[0,1,0]]
                    }
                    """)
                    //.userid(12L)
                    .userid(user_id)
                    .build();

            siteService.save(site);
            System.out.println("saved new user with grid data");
        }




        return "index";
    }

   // @GetMapping("/error")
   // public String error() {
    //    return "error";
    //}
}
