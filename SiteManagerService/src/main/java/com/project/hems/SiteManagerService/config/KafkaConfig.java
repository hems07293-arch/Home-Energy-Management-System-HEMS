package com.project.hems.SiteManagerService.config;

import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConfigurationProperties(prefix = "property.config.kafka")
@Setter
public class KafkaConfig {

    public String siteCreationTopic;
    public Integer siteCreationPartitionCount;
    public Integer replicaCount;


    @Bean
    public NewTopic siteCreationTopic(){
        return TopicBuilder.name(siteCreationTopic)
                .partitions(siteCreationPartitionCount)
                .replicas(replicaCount)
                .build();

    }

}
