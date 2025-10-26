package com.silva.securityservice.service;

import com.silva.securityservice.exception.RoleNotFoundException;
import com.silva.securityservice.exception.UserFoundException;
import com.silva.securityservice.model.dto.UserCreationRequest;
import com.silva.securityservice.model.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserCreationRequest userCreationRequest) throws RoleNotFoundException, UserFoundException;
}
