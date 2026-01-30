package com.project.hems.envoy_manager_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.DefaultClientCredentialsTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2ClientCredentialsGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2ClientCredentialsGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "property.config.security.oauth2.auth0")
@Setter
public class OAuth2ClientConfig {

        private String audience;

        @Bean
        public OAuth2AuthorizedClientManager authorizedClientManager(
                        ClientRegistrationRepository clientRegistrationRepository,
                        OAuth2AuthorizedClientService authorizedClientService) {

                OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder()
                                .clientCredentials(builder -> builder.accessTokenResponseClient(
                                                auth0TokenResponseClient()))
                                .build();

                AuthorizedClientServiceOAuth2AuthorizedClientManager manager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                                clientRegistrationRepository,
                                authorizedClientService);

                manager.setAuthorizedClientProvider(provider);
                return manager;
        }

        private OAuth2AccessTokenResponseClient<OAuth2ClientCredentialsGrantRequest> auth0TokenResponseClient() {

                DefaultClientCredentialsTokenResponseClient client = new DefaultClientCredentialsTokenResponseClient();

                client.setRequestEntityConverter(request -> {
                        RequestEntity<?> entity = new OAuth2ClientCredentialsGrantRequestEntityConverter()
                                        .convert(request);

                        MultiValueMap<String, String> body = new LinkedMultiValueMap<>(
                                        (MultiValueMap<String, String>) entity.getBody());

                        body.add("audience", audience);

                        return new RequestEntity<>(
                                        body,
                                        entity.getHeaders(),
                                        entity.getMethod(),
                                        entity.getUrl());
                });

                return client;
        }

}
