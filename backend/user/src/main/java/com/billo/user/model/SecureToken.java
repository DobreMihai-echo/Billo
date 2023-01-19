package com.billo.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "secureTokens")
@Data
public class SecureToken{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String token;

    @Column(unique = true)
    private String phoneCode;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime localDateTime;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expireAt;

    @Column(nullable = true)
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private AppUser user;

    @Transient
    private boolean isExpired;

    public boolean isExpired() {
        return getExpireAt().isBefore(LocalDateTime.now()); // this is generic implementation, you can always make it timezone specific
    }
    //getter an setter
}
