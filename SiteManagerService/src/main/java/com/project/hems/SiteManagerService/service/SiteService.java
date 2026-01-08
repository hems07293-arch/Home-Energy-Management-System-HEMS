package com.project.hems.SiteManagerService.service;

import com.project.hems.SiteManagerService.dto.SiteRequestDto;
import com.project.hems.SiteManagerService.entity.*;
import com.project.hems.SiteManagerService.exception.ResourceNotFoundException;
import com.project.hems.SiteManagerService.repository.OwnerRepo;
import com.project.hems.SiteManagerService.repository.SiteRepo;
import com.project.hems.SiteManagerService.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SiteService {

    private final SiteRepo siteRepo;
    private final OwnerRepo ownerRepo;
    private final ValueMapper valueMapper;

    public Site createSite(SiteRequestDto dto) {

        //in dto apde id store kariee chiee owner entity ni so apde ema thi fetch karine obj banavsu
        Owner owner = ownerRepo.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

        // Save owner first in new created site
        Owner savedOwner = ownerRepo.save(owner);

        //now we create new Site obj and eni under badhu set karsu
        Site site=new Site();
        site.setOwner(savedOwner);
        site.setActive(true);
        site.setEnrollProgramIds(dto.getProgramId());

        //now have child na table jode thi badhu laisu save karsu then site ma save karsu
        //solar
        if(dto.getSolars()!=null){
           List<Solar> solarList= dto.getSolars().stream()
                    .map(solarDto->{
                          Solar solar=valueMapper.solarDtoToModel(solarDto,site);
                          return solar;
                    }).toList();
            site.setSolar(solarList);
        }

        //battery
        if(dto.getBattery()!=null){
            Battery battery=valueMapper.batteryDtoToModel(dto.getBattery(),site);
            site.setBattery(battery);
        }

        //address
        if(dto.getAddress()!=null){
            Address address=valueMapper.addressDtoToModel(dto.getAddress(),site);
            site.setAddress(address);
        }

        Site savedSite=siteRepo.save(site);



        //todo:-
        //site ni pan dto banavine work karvu siteResponseDto che toh e pass karvo
        return savedSite;

    }
}

