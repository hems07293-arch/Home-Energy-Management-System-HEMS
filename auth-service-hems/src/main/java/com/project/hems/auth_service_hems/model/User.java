package com.project.hems.auth_service_hems.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
//@Table(name = "users",
//        uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"provider","providerUserId"})
//        })
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false,unique = true)
    private Long id;
    @Column(name = "user_id",nullable = false,updatable = false)
    private Long userId;
    @Column(name = "provider")
    private String provider;
    @Column(name = "provider_user_id")
    private String providerUserId;
    @Column(name = "provider_sub")
    private String providerSub;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "time",nullable = false,updatable = true)
    private LocalDateTime time;

}
