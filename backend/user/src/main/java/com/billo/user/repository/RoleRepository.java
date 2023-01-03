package com.billo.user.repository;

import com.billo.user.model.AppRole;
import com.billo.user.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<AppRole,Long> {

    Optional<AppRole> findByName(ERole name);
}
