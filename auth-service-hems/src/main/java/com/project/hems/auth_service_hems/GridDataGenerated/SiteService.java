package com.project.hems.auth_service_hems.GridDataGenerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteService {

    @Autowired
    private SiteRepo siteRepo;

    public Site save(Site site){
        Site savedGrid=siteRepo.save(site);
        return savedGrid;
    }

    public boolean findEmail(String email){
        return siteRepo.existsByEmail(email);
    }
}
