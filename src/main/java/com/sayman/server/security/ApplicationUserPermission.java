package com.sayman.server.security;

public enum ApplicationUserPermission {
    CALENDAR_READ("calendar:read"),
    CALENDAR_WRITE("calendar:write"),
    SUBSCRIBER_READ("subscriber:read"),
    SUBSCRIBER_WRITE("subscriber:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
