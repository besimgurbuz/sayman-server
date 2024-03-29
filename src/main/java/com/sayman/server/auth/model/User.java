package com.sayman.server.auth.model;

import com.sayman.server.security.ApplicationUserRole;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
public class User implements Serializable {

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

    @Column(name = "account_non_expired")
    private boolean isAccountNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean isAccountNonLocked = true;

    @Column(name = "credentials_non_expired")
    private boolean isCredentialsNonExpired = true;

    @Column(name = "enabled")
    private boolean isEnabled = true;

    public UserDto convertToUserDto() {
        return UserDto.builder()
                .username(username)
                .email(email)
                .role(role)
                .build();
    }
}
