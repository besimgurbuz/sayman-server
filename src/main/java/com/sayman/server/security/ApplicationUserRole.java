package com.sayman.server.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sayman.server.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Set.of(CALENDER_READ, CALENDER_WRITE, SUBSCRIBER_READ, SUBSCRIBER_WRITE)),
    MANAGER(Set.of(CALENDER_READ, CALENDER_WRITE)),
    REVIEWER(Set.of(CALENDER_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
