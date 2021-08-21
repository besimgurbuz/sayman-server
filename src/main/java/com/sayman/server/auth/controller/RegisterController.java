package com.sayman.server.auth.controller;

import com.sayman.server.auth.model.UserDto;
import com.sayman.server.auth.service.RegisterService;
import com.sayman.server.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/register")
public class RegisterController {
   @Autowired
   private RegisterService registerService;

   @SuppressWarnings({"rawtypes"})
   @PostMapping
   public ResponseEntity<?> registerNewUserIfNotExists(@Valid @RequestBody UserDto userDto) {
      try {
         UserDto newUser = registerService.createNewUser(userDto);
         return new ResponseEntity<>(newUser, HttpStatus.CREATED);
      } catch (UnsupportedOperationException e) {
         return new ResponseEntity<>(new CustomErrorType("A user with username "
                 + userDto.getUsername() + " already exists"), HttpStatus.CONFLICT);
      }
   }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
      Map<String, String> errors = new HashMap<>();
      for (ObjectError error : exception.getBindingResult().getAllErrors()) {
         String fieldName = ((FieldError) error).getField();
         String errorMessage = error.getDefaultMessage();
         errors.put(fieldName, errorMessage);
      }
      return errors;
   }
}
