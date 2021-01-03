package com.sayman.server.auth.model;

import com.sayman.server.security.ApplicationUserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private ApplicationUserRole role;

    private String email;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    private String password;

    @Column(name = "account_non_expired", columnDefinition = "boolean default true")
    private boolean isAccountNonExpired;

    @Column(name = "account_non_locked", columnDefinition = "boolean default true")
    private boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired", columnDefinition = "boolean default true")
    private boolean isCredentialsNonExpired;

    @Column(name = "enabled", columnDefinition = "boolean default true")
    private boolean isEnabled;
}
