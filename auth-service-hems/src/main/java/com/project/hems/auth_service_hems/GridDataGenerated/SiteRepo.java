package com.project.hems.auth_service_hems.GridDataGenerated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepo extends JpaRepository<Site,Long> {

    boolean existsByEmail(String email);

}
