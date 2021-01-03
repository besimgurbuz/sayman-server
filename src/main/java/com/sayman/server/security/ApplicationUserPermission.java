package com.sayman.server.security;

public enum ApplicationUserPermission {
    CALENDER_READ("calender:read"),
    CALENDER_WRITE("calender:write"),
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
