package com.billo.user.repository;

import com.billo.user.model.AppUser;
import com.billo.user.model.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken,Long> {

    Optional<SecureToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE SecureToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);

    SecureToken findByUser(AppUser user);
}
