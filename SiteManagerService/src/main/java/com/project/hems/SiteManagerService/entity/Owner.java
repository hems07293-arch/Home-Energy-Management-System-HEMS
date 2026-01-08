package com.project.hems.SiteManagerService.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "owners")
@Data
@ToString(exclude = "sites")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //wrote validation here
    @NotEmpty(message = "owner name cannot be empty")
    @Size(max = 50, message = "Name length must not exceed 50")
    private String ownerName;

    @NotEmpty(message = "email name cannot be empty")
    @Email(message = "email should be valid")
    private String email;

    @Size(max=10,message = "minimum 10 digit must be needed")
    @NotEmpty(message = "phone number cannot be empty")
    private String phoneNo;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Site> sites;

}
