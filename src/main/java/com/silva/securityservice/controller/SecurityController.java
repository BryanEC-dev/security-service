package com.silva.securityservice.controller;

import com.silva.securityservice.exception.RoleNotFoundException;
import com.silva.securityservice.exception.UserFoundException;
import com.silva.securityservice.model.dto.UserCreationRequest;
import com.silva.securityservice.model.dto.UserResponse;
import com.silva.securityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/users")
//@RequiredArgsConstructor //TODO
public class SecurityController {

    @Autowired
    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreationRequest userCreationRequest) throws RoleNotFoundException, UserFoundException {
        UserResponse createdUser = userService.createUser(userCreationRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
