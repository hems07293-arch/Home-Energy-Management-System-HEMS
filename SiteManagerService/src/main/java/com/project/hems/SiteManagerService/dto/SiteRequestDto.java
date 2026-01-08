package com.project.hems.SiteManagerService.dto;

import com.project.hems.SiteManagerService.entity.Address;
import com.project.hems.SiteManagerService.entity.Battery;
import com.project.hems.SiteManagerService.entity.Owner;
import com.project.hems.SiteManagerService.entity.Solar;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SiteRequestDto {
    private UUID ownerId;
    private boolean isActive;
    private List<SolarDto> solars;
    private BatteryDto battery;
    private AddressDto address;
    private List<UUID> programId;//jema user e ui mathi select karyu hase
}
