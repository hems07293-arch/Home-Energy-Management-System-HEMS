package com.hems.project.Virtual_Power_Plant.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hems.project.Virtual_Power_Plant.dto.OwnerDto;
import com.hems.project.Virtual_Power_Plant.external.SiteFeignClientService;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SiteCreationService {

    private final SiteFeignClientService service;

    
    // public ResponseEntity<List<Object>> fetchAllSites(){
    //    return service.getAllSites();
    // }

        public ResponseEntity<List<OwnerDto>> fetchAllOnwer(){
          return service.getAllOwner();
        }
        

        
      
        


    
}
