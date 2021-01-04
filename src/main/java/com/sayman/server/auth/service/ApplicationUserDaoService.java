package com.sayman.server.auth.service;

import com.google.common.collect.ImmutableList;
import com.sayman.server.auth.model.ApplicationUser;
import com.sayman.server.auth.dao.ApplicationUserDao;
import com.sayman.server.auth.model.User;
import com.sayman.server.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationUserDaoService implements ApplicationUserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    public List<ApplicationUser> getApplicationUsers() {
        List<User> users = ImmutableList.copyOf(userRepository.findAll());

        return users.stream()
                .map(user ->
                        new ApplicationUser(
                                user.getUsername(),
                                user.getPassword(),
                                user.getRole().getGrantedAuthorities(),
                                user.isAccountNonExpired(),
                                user.isAccountNonLocked(),
                                user.isCredentialsNonExpired(),
                                user.isEnabled())).collect(Collectors.toList());
    }
}
