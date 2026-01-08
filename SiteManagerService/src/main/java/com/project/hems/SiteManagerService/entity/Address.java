package com.project.hems.SiteManagerService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "addressLine1 cannot be empty")
    @Size(min = 7, max = 30, message = "addressLine1 length must be between 7 and 30")
    private String addressLine1;

    @Size(min = 7, max = 30, message = "addressLine2 length must be between 7 and 30")
    private String addressLine2;

    @NotBlank(message = "city cannot be empty")
    @Size(max = 25, message = "city length must not exceed 25")
    private String city;

    @NotBlank(message = "state cannot be empty")
    @Size(max = 25, message = "state length must not exceed 25")
    private String state;

    @NotBlank(message = "postalCode cannot be empty")
    @Size(max = 10, message = "postalCode length must not exceed 10")
    private String postalCode;

    @NotBlank(message = "county cannot be empty")
    @Size(max = 25, message = "county length must not exceed 25")
    private String county;


    @OneToOne
    @JoinColumn(name = "site_id")
    @JsonBackReference
    @NotNull(message = "site entity cannot be null")
    private Site site;



}

