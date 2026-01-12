package com.project.hems.auth_service_hems.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_identities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserIdentitie {

    /*
    id (PK)
    user_id (FK → users.id)
    provider (auth0 / google / github)
    provider_sub (UNIQUE)
    created_at
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_sub")
    private String providerSub;

    @Column(name = "created_at",nullable = false,updatable = true)
    private LocalDateTime created_time;



}
