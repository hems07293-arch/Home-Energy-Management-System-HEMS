package com.project.hems.auth_service_hems.GridDataGenerated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolarOutput{
private int batterySoc;
private int solarPower;
private boolean dispatchActive;
private LocalDateTime lastUpdated;

}
