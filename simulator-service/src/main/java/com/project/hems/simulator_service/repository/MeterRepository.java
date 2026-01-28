package com.project.hems.simulator_service.repository;

import com.project.hems.simulator_service.domain.MeterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MeterRepository extends JpaRepository<MeterEntity, UUID> {
    Optional<MeterEntity> findBySiteId(UUID siteId);
}
