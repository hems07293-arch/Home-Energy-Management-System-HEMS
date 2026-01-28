package com.project.hems.auth_service_hems.repository;

import com.project.hems.auth_service_hems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    // note findBy method entity return kare so make sure apde @Query na madad thi
    // apde apdi jate query lakhiee ke su joiee che
    // @Query("SELECT u.userId FROM User u WHERE u.email = :email")
    // Optional<Long> findUserIdByEmail(@Param("email") String email);

    // @Query("SELECT u.userId FROM User u WHERE u.providerSub = :providerSub")
    // Optional<Long> findUserIdByProviderSub(@Param("providerSub") String
    // providerSub );

    // ama entity ni filedname thi query banavani table na name thi nai ..
    // boolean existsByProviderUserId(String providerUserId);

    // Optional<User> findByProviderAndProviderUserId(
    // String provider,
    // String providerUserId
    // );

    Optional<User> findByEmail(String email);
    // Optional<User> findByProviderSub(String providerSub);

}
