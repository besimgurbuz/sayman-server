package com.sayman.server.auth.model;

import com.sayman.server.security.ApplicationUserRole;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class UserDto {
    private String username;
    private ApplicationUserRole role;
    private String email;
    private LocalDateTime createdAt;
}
