package com.project.hems.simulator_service.model.envoy;

import com.project.hems.simulator_service.model.BatteryMode;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BatteryControl {
    private BatteryMode mode;
    private Long targetPowerW;
    private Long maxChargeW;
    private Long maxDischargeW;
    private Double minSocPercent;
    private Double maxSocPercent;
}
