package com.sayman.server.authentication.controller;

import com.sayman.server.authentication.model.User;
import com.sayman.server.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public User createNewUser(@RequestBody User user) {
         return authenticationService.createNewUser(user);
    }
}
