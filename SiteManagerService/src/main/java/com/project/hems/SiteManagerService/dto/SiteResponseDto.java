package com.project.hems.SiteManagerService.dto;

import com.project.hems.SiteManagerService.entity.Address;
import com.project.hems.SiteManagerService.entity.Battery;
import com.project.hems.SiteManagerService.entity.Owner;
import com.project.hems.SiteManagerService.entity.Solar;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SiteResponseDto {
    //change karelu ch he UUID -> Long
    private Long siteId;
    private Owner owner;
    private boolean isActive;
    private List<SolarDto> solars;
    private BatteryDto batteryInfo;
    private AddressDto addressInfo;
    private List<UUID> programId;

}
