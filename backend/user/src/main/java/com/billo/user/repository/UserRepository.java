package com.billo.user.repository;

import com.billo.user.model.AppRole;
import com.billo.user.model.AppUser;
import com.billo.user.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    List<AppUser> findAllUsersByRolesAndAccountVerified(AppRole role, Boolean accountVerified);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.accountVerified = TRUE WHERE a.username = ?1")
    int enableAppUser(String username);
}
