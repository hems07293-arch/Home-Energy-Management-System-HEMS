package com.project.hems.SiteManagerService.service;

import com.project.hems.SiteManagerService.dto.OwnerDto;
import com.project.hems.SiteManagerService.entity.Owner;
import com.project.hems.SiteManagerService.entity.OwnerIdentities;
import com.project.hems.SiteManagerService.exception.ResourceNotFoundException;
import com.project.hems.SiteManagerService.repository.OwnerIdentityRepo;
import com.project.hems.SiteManagerService.repository.OwnerRepo;
import com.project.hems.SiteManagerService.repository.SiteRepo;
import com.project.hems.SiteManagerService.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepo ownerRepo;
    private final SiteRepo siteRepo;
    private final ValueMapper valueMapper;
    private final OwnerIdentityRepo ownerIdentityRepo;

    @Transactional
    public OwnerDto createOwner(Owner owner, String userSub, String email) {
        // extract provider
        String sub = userSub;// auth0|695781256ccfb90819e1df58
        String[] parts = sub.split("\\|");// [auth0,695781256ccfb90819e1df58]
        String provider = parts[0];

        Owner savedOwner = ownerRepo.findByEmail(email).orElseGet(() -> {
            owner.setEmail(email);
            return ownerRepo.save(owner);
        });

        if (!ownerIdentityRepo.existsByAuthSub(userSub)) {
            OwnerIdentities ownerIdentities = OwnerIdentities.builder()
                    .authSub(userSub)
                    .provider(provider)
                    .owner(savedOwner)
                    .build();
            ownerIdentityRepo.save(ownerIdentities);
        }

        return valueMapper.ownerModelToDto(savedOwner);
    }

    public OwnerDto getOwnerDetail(UUID id) {
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("owner details not found for this id : " + id));
        OwnerDto ownerDto = valueMapper.ownerModelToDto(owner);
        return ownerDto;
    }

    public OwnerDto updateOwnerDetail(Owner owner) {
        Owner existingOwner = ownerRepo.findById(owner.getId())
                .orElseThrow(() -> new ResourceNotFoundException("owner is not found for this is : " + owner.getId()));
        Optional.ofNullable(owner.getEmail()).ifPresentOrElse(existingOwner::setEmail, () -> {
            System.out.println("id is not provided for updating owner");
            // todo:-
            // aa else vadi condition kadhi nakhvi
        });
        Optional.ofNullable(owner.getOwnerName()).ifPresent(existingOwner::setOwnerName);
        Optional.ofNullable(owner.getSites()).ifPresent(existingOwner::setSites);
        Optional.ofNullable(owner.getPhoneNo()).ifPresent(existingOwner::setPhoneNo);
        ownerRepo.save(existingOwner);
        OwnerDto existingOwnerDto = valueMapper.ownerModelToDto(existingOwner);

        return existingOwnerDto;
    }

    @Transactional
    public void deleteOwner(UUID ownerId) {
        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("owner details not found for this id : " + ownerId));
        siteRepo.deleteByOwner(owner);// because owner parent table che and site child table che so apde direct owner
        // delete kasu without deeting ownerSite so error apse ke first child table
        // mathi remove karo
        ownerRepo.deleteById(ownerId);
    }

    public List<OwnerDto> getAllOwnerDetail() {
        List<Owner> allOwner = ownerRepo.findAll();
        List<OwnerDto> allOwnerDto = allOwner.stream()
                .map(owner -> {
                    OwnerDto ownerDto = valueMapper.ownerModelToDto(owner);
                    return ownerDto;
                }).toList();
        System.out.println("Disptach from thared :- " + Thread.currentThread());
        return allOwnerDto;
    }
}
