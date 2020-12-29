package com.sayman.server.authentication.service;

import com.sayman.server.authentication.model.User;
import com.sayman.server.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public User createNewUser(User newUser) {
        return userRepository.save(newUser);
    }
}
