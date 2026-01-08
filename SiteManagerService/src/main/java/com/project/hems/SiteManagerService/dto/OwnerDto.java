package com.project.hems.SiteManagerService.dto;

import com.project.hems.SiteManagerService.entity.Site;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OwnerDto {
    private UUID id;
    private String ownerName;
    private String email;
    private String phoneNo;
    private List<UUID> sitesIds;

}
