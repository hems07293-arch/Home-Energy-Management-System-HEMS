package com.hems.project.Virtual_Power_Plant.controller;

import com.hems.project.Virtual_Power_Plant.entity.Region;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic", groupId = "my-new-group")
    public void listen1(Region region, Acknowledgment ack) {

        System.out.println("Received: " + region);

        if("surat".equals(region.getName())) {
            System.out.println("❌ Simulating failure, NOT acknowledging");
            throw new RuntimeException("Test failure");
        }

        ack.acknowledge();
    }

}










