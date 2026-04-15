package com.project.hems.program_enrollment_manager.config;

import org.springframework.context.annotation.Bean;

import feign.codec.ErrorDecoder;

public class SiteFeignConfig {

    @Bean
    public ErrorDecoder siteErrorDecoder() {
        return new FeignSiteErrorDecoder();
    }
}
