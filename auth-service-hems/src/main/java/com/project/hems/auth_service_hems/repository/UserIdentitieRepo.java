package com.project.hems.auth_service_hems.repository;

import com.project.hems.auth_service_hems.model.UserIdentitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserIdentitieRepo extends JpaRepository<UserIdentitie, UUID> {
    Optional<UserIdentitie> findByProviderSub(String providerSub);
}
