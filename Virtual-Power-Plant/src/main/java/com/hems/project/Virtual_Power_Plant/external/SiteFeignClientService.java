package com.hems.project.Virtual_Power_Plant.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.hems.project.Virtual_Power_Plant.dto.OwnerDto;

@FeignClient(name = "SITE-SERVICE-HEMS")
public interface SiteFeignClientService {
    
        // @GetMapping("/api/v1/site/fetch-all-site")
        // public ResponseEntity<List<Object>> getAllSites();

        @GetMapping("/api/v1/owner/fetch-all-owner")
        public ResponseEntity<List<OwnerDto>> getAllOwner();
}
