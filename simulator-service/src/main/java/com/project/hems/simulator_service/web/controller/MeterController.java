package com.project.hems.simulator_service.web.controller;

import com.project.hems.simulator_service.config.ActiveControlStore;
import com.project.hems.simulator_service.model.ActiveControlState;
import com.project.hems.simulator_service.model.MeterSnapshot;
import com.project.hems.simulator_service.model.envoy.DispatchCommand;
import com.project.hems.simulator_service.service.MeterManagementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/simulation")
@RequiredArgsConstructor
public class MeterController {

    private final MeterManagementService meterManagementService;
    private final Map<String, MeterSnapshot> meterReadings;
    private final ActiveControlStore activeControlStore;

    @GetMapping("/get-meter-data/{siteId}")
    public ResponseEntity<MeterSnapshot> getMeterData(@PathVariable(name = "siteId", required = true) UUID siteId) {
        log.info("get meter data for siteId: {}", siteId);
        return new ResponseEntity<>(meterManagementService.getMeterData(siteId), HttpStatus.OK);
    }

    @GetMapping("/get-meter-id/{siteId}")
    public Long findMeterIdBySiteId(@PathVariable(name = "siteId", required = true) UUID siteId) {
        log.info("getMeterId: GET request to fetch meter id from siteId " + siteId);
        return meterManagementService.getMeterData(siteId).getMeterId();
    }

    @GetMapping("/get-all-meter-data")
    public Map<String, MeterSnapshot> getAllMeterData() {
        log.info("get meter data");
        return meterReadings;
    }

    @PostMapping("/activate-meter/{siteId}")
    public void activateMeterData(@PathVariable(name = "siteId", required = true) UUID siteId,
            @RequestBody Double batteryCapacity) {
        log.info("activate meter: {}", siteId, batteryCapacity);
        meterManagementService.activateMeter(siteId, batteryCapacity);
    }

    @PostMapping("/dispatch")
    public ResponseEntity<Void> applyDispatch(@RequestBody @Valid DispatchCommand command) {
        log.info("applyDispatch: applying dispatch command received from envoy " + command);
        ActiveControlState control = new ActiveControlState(
                command.getBatteryControl(),
                command.getGridControl(),
                command.getEnergyPriority(),
                command.getValidUntil());

        activeControlStore.applyDispatch(command.getSiteId(), control);

        return ResponseEntity.accepted().build();
    }

}
