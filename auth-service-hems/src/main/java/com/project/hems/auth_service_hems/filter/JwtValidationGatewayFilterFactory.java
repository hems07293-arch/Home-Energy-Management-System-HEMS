package com.project.hems.auth_service_hems.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
//
//public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
//
//    private final WebClient webClient;
//
//    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder){
//        this.webClient=webClientBuilder.build();
//    }
//
//    @Override
//    public GatewayFilter apply(Object config) {
//        return (exchange, chain)-> {
//            String token=exchange.getRequest().getHeaders().getFirst("AUTHORIZATION");
//            if(token==null||!token.startsWith("Bearer ")){
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//
//            return webClient.get()
//                    .uri("/validate")
//                    .header(HttpHeaders.AUTHORIZATION,token)
//                    .retrieve()
//                    .toBodilessEntity()
//                    .then(chain.filter(exchange));
//        };
//    }
//}



