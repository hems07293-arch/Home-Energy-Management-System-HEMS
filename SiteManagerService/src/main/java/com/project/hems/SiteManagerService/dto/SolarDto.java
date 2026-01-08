package com.project.hems.SiteManagerService.dto;

import com.project.hems.SiteManagerService.entity.Site;
import lombok.Data;

import java.util.UUID;
@Data
public class SolarDto {
    private UUID id;
    private double totalPanelCapacity;
    private double inverterMaxCapacity;
    private String orientation;
    private UUID siteId;
}

