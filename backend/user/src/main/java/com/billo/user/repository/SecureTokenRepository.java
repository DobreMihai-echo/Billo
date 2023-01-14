package com.billo.user.repository;

import com.billo.user.model.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken,Long> {
}
