package com.project.hems.envoy_manager_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.hems.envoy_manager_service.model.SiteControlCommand;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimulationConnectorService {
    private final SimulatorFeignClientService simulatorFeignClientService;

    public void applyControlToSimulation(SiteControlCommand command) {
        UUID siteId = command.getSiteId();

        try {
            log.info("Applying Dispatch Control to Meter {}", siteId);

            // Single Call to Update Simulation Settings
            simulatorFeignClientService.applyDispatch(command);

            log.info("Successfully reconfigured Simulation for Dispatch ID: {}", command.getDispatchId());

        } catch (

        Exception e) {
            log.error("Failed to push config to Simulation Service: " + e.getMessage());
            // TODO: Retry logic or alert Dispatch Manager of failure
        }
    }
}
