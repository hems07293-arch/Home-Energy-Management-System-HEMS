package com.project.hems.auth_service_hems.GridDataGenerated;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

//new user avse toh ene apde modbudPal mathi generated data laine database ma store karsu ...
//same user different method mathi karse signup toh nai thayy
@Entity
@Table(name = "site")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Site{

    @Id
    @GeneratedValue(generator = "site-id-generator")
    @GenericGenerator(
            name = "site-id-generator",
            strategy = "com.project.hems.auth_service_hems.GridDataGenerated.SiteIdGenerator"
    )
    @Column(nullable = false, updatable = false)
    private String id;


    @Column(name = "user_id", unique = true,nullable = false)
    private Long userid;

    @Column(columnDefinition = "json", nullable = false)
    private String site_data;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(columnDefinition = "json")
    private String homeAppliances; //example:- tv,ac,fridge

    private String homeAppliancesTotalPowerRequirement;// example:- 500W

    private int totalHomeAppliances; // example:- 3
    //pachi user UI mathi add kare toh ene apde aa count ma add karai sakiee

    @Column(columnDefinition = "json")
    private String inverterCapacity;//5000W

    private String currentInverterPower;//hamda 200W che current

    @Column(columnDefinition = "json")
    private String siteEnrollProgramList;

}


