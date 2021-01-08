package com.sayman.server.auth.controller;

import com.sayman.server.auth.model.User;
import com.sayman.server.auth.model.UserDto;
import com.sayman.server.auth.service.RegisterService;
import com.sayman.server.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/register")
public class RegisterController {
   @Autowired
   private RegisterService registerService;

   @SuppressWarnings({"rawtypes"})
   @PostMapping
   public ResponseEntity<?> registerNewUserIfNotExists(@RequestBody User user) {
      try {
         User newUser = registerService.createNewUser(user);
      } catch (UnsupportedOperationException e) {
         return new ResponseEntity<>(new CustomErrorType("A user with username "
                 + user.getUsername() + " already exists"), HttpStatus.CONFLICT);
      }

      return new ResponseEntity<UserDto>(HttpStatus.CREATED);
   }
}
