package com.project.hems.simulator_service.config;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.hems.simulator_service.model.MeterSnapshot;

@Configuration
public class MeterReadingMap {

    @Bean
    public Map<UUID, MeterSnapshot> getMeterMap() {
        return new ConcurrentHashMap<>();
    }
}
