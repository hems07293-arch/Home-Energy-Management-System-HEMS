package com.project.hems.envoy_manager_service.model;

import com.project.hems.envoy_manager_service.model.simulator.BatteryMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatteryControl {
    private BatteryMode mode;
    private Long targetPowerW;
    private Long maxChargeW;
    private Long maxDischargeW;
    private Double minSocPercent;
    private Double maxSocPercent;
}
