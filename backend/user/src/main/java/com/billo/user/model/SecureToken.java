package com.billo.user.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "secureTokens")
@Data
public class SecureToken{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @Column(unique = true)
    private String phoneCode;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp timeStamp;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expireAt;

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
