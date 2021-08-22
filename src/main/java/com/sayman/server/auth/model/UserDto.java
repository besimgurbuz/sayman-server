package com.sayman.server.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sayman.server.security.ApplicationUserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Optional;

@Builder
@Getter
@Setter
public class UserDto {
    @NotNull(message = "Username cannot be empty")
    @Pattern(regexp = "^(?=.{5,20}$)[a-zA-Z0-9_]+$", message = "Username must be between 5 and 20 characters " +
            "long and can only contain letters, numbers and underscores.")
    private String username;
    private ApplicationUserRole role;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public User convertToUser() {
        return User.builder()
                .username(this.username)
                .role(Optional.ofNullable(this.role).orElseGet(() -> ApplicationUserRole.REVIEWER))
                .password(this.password)
                .email(this.email)
                .createdAt(LocalDateTime.now())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
    }
}
