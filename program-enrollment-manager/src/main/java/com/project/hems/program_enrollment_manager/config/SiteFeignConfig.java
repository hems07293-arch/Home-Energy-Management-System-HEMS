package com.project.hems.program_enrollment_manager.config;

import feign.codec.ErrorDecoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiteFeignConfig {

    @Bean
    public ErrorDecoder siteErrorDecoder() {
        return new FeignSiteErrorDecoder();
    }

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }
}