package com.project.hems.program_enrollment_manager.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

import org.springframework.context.annotation.Bean;

public class ExcelFeignConfig {

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }
}