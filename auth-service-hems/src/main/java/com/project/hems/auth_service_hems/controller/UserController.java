package com.project.hems.auth_service_hems.controller;

import com.project.hems.auth_service_hems.model.User;
import com.project.hems.auth_service_hems.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final OAuth2AuthorizedClientService clientService;

    //change user to userDto in response
//    @PostMapping("/create-user")
//    public ResponseEntity<User> createUser(@RequestBody User user){
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user, user.getEmail()));
//    }

    @GetMapping("/")
    public String createUser(Model model, @AuthenticationPrincipal OidcUser oidcUser){

        if(oidcUser==null){
            return "index";
        }

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


        String email=oidcUser.getEmail();
        String subject=oidcUser.getSubject();

        User user=userService.loginOrRegister(email,subject);

        model.addAttribute("user",user);
        model.addAttribute("profile", oidcUser.getClaims());

        return "index";
    }
}
