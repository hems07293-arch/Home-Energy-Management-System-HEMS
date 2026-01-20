package com.project.hems.api_gateway_hems.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Configuration
public class TokenLoggingFilter {

    private final ReactiveOAuth2AuthorizedClientService clientService;

    public TokenLoggingFilter(ReactiveOAuth2AuthorizedClientService clientService) {
        this.clientService = clientService;
    }

    //GlobalFilter name nu je pan spring Bean hase e api gateway ni koi pan request hase te
    //automatic run thase apde pehle Onceperrequest and AbstractGateway evu karvani jarur nathi ..
    //this is global filter
    @Bean
    public GlobalFilter logAccessTokenFilter() {
        return (exchange, chain) ->
                exchange.getPrincipal()
                        .cast(OAuth2AuthenticationToken.class)
                        .flatMap(auth ->

                                clientService
                                        .loadAuthorizedClient(
                                                auth.getAuthorizedClientRegistrationId(),
                                                auth.getName()
                                        )
                                        .doOnNext(client -> {
                                            System.out.println("Access token:");
                                            System.out.println(client.getAccessToken().getTokenValue());
                                        })
                                        .then(chain.filter(exchange))
                        );
    }
}


