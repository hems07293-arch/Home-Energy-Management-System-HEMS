package com.project.hems.envoy_manager_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FeignOAuth2Config {

    private final OAuth2AuthorizedClientManager clientManager;

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {

            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId("auth0") // <-- same id as yml
                    .principal("kafka-consumer") // any non-null string
                    .build();

            OAuth2AuthorizedClient authorizedClient = clientManager.authorize(authorizeRequest);

            if (authorizedClient == null) {
                throw new IllegalStateException("Failed to authorize OAuth2 client");
            }

            String accessToken = authorizedClient.getAccessToken().getTokenValue();

            requestTemplate.header("Authorization", "Bearer " + accessToken);
        };
    }
}
