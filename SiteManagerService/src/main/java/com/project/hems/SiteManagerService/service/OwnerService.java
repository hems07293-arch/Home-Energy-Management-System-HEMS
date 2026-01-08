package com.project.hems.SiteManagerService.service;

import com.project.hems.SiteManagerService.dto.OwnerDto;
import com.project.hems.SiteManagerService.entity.Owner;
import com.project.hems.SiteManagerService.exception.ResourceNotFoundException;
import com.project.hems.SiteManagerService.repository.OwnerRepo;
import com.project.hems.SiteManagerService.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepo ownerRepo;
    private final ValueMapper valueMapper;

    public OwnerDto createOwner(Owner owner){
        Owner savedOwner=ownerRepo.save(owner);
        OwnerDto ownerDto=valueMapper.ownerModelToDto(owner);
        return ownerDto;
    }

    public OwnerDto getOwnerDetail(UUID id){
        Owner owner=ownerRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("owner details not found for this id : "+id));
        OwnerDto ownerDto=valueMapper.ownerModelToDto(owner);
        return ownerDto;
    }
}
