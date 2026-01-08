package com.project.hems.SiteManagerService.controller;


import com.project.hems.SiteManagerService.dto.OwnerDto;
import com.project.hems.SiteManagerService.entity.Owner;
import com.project.hems.SiteManagerService.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping("/create-owner")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerDto> createOwner(@RequestBody Owner owner){
        OwnerDto savedOwner=ownerService.createOwner(owner);
        return new ResponseEntity<>(savedOwner,HttpStatus.CREATED);
    }

    @GetMapping("/get-owner-by-id/{ownerId}")
    public ResponseEntity<OwnerDto> getOwner(@PathVariable UUID ownerId){
        OwnerDto ownerDto=ownerService.getOwnerDetail(ownerId);
        return new ResponseEntity<>(ownerDto,HttpStatus.OK);
    }
}
