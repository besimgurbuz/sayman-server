package com.sayman.server.auth.dao;

import com.sayman.server.auth.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDao {
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
