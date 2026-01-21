package com.project.hems.dispatch_manager_service.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class DispatchEvent {
    private Long dispatchId;
    private Long siteId;
    private DispatchEventType eventType;
    private Long powerReqW;
    private Long durationSec;
    private Set<EnergyPriority> energyPriority;
    private String reason;
}
