package com.sayman.server.auth.service;

import com.sayman.server.auth.model.User;
import com.sayman.server.auth.model.UserDto;
import com.sayman.server.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto createNewUser(UserDto newUser) {
        Optional<User> foundUser = userRepository.findByUsername(newUser.getUsername());

        if (foundUser.isPresent()) {
            throw new UnsupportedOperationException(String.format("A user with %s username already exists",
                    foundUser.get().getUsername()));
        }

        Optional<User> foundUserByEmail = userRepository.findByEmail(newUser.getEmail());

        if (foundUserByEmail.isPresent()) {
            throw new UnsupportedOperationException(String.format("A user with %s email address already exists",
                    foundUserByEmail.get().getEmail()));
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User savedUser = userRepository.save(newUser.convertToUser());

        return savedUser.convertToUserDto();
    }
}
