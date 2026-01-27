package com.project.hems.SiteManagerService.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class KafkaSiteProducerDto {
    private UUID siteId;
}
