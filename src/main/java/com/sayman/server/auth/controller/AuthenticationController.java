package com.sayman.server.auth.controller;

import com.sayman.server.auth.model.UserDto;
import com.sayman.server.auth.model.User;
import com.sayman.server.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public UserDto createNewUser(@RequestBody User user) {
         return authenticationService.createNewUser(user).convertToUserDto();
    }

    @GetMapping(value = "/calender")
    @PreAuthorize("hasAuthority('calender:read')")
    public String getInfo() {
        return "INFO";
    }

    @PostMapping(value = "/calender")
    @PreAuthorize("hasAuthority('calender:write')")
    public String createBody(@RequestBody String body) {
        return "You created " + body;
    }

    @GetMapping(value = "/calender/insight")
    @PreAuthorize("hasAuthority('calender:write')")
    public String calenderInsight() {
        return "This is calender insight";
    }

    @GetMapping(value = "/subscribers")
    @PreAuthorize("hasAuthority('subscriber:read')")
    public List<String> getSubscribers() {
       return List.of(
              "Anna",
               "Tom",
               "Linda"
       );
    }
}
