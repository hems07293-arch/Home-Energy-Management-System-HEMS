package com.project.hems.SiteManagerService.dto;
import com.project.hems.SiteManagerService.entity.Site;
import lombok.Data;

import java.util.UUID;
@Data
public class BatteryDto {
    private UUID id;
    private int quantity;
    private double capacity;
    private double maxOutput;
    //change karelu che UUID -> Long
    private Long siteId;
}
