package com.project.hems.SiteManagerService.repository;

import com.project.hems.SiteManagerService.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SiteRepo extends JpaRepository<Site, UUID> {


}
