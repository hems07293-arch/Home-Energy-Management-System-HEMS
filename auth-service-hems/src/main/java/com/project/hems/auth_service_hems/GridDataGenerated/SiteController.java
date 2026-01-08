package com.project.hems.auth_service_hems.GridDataGenerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grid")
public class SiteController {

    @Autowired
    private SiteService gridService;

    @PostMapping("/create")
    public ResponseEntity<Site> saveGrid(@RequestBody Site grid){
        return ResponseEntity.status(HttpStatus.CREATED).body(gridService.save(grid));
    }
}
