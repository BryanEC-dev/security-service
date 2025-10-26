package com.silva.securityservice.service.impl;

import com.silva.securityservice.exception.RoleNotFoundException;
import com.silva.securityservice.exception.UserFoundException;
import com.silva.securityservice.model.dto.UserCreationRequest;
import com.silva.securityservice.model.dto.UserResponse;
import com.silva.securityservice.model.entity.RoleEntity;
import com.silva.securityservice.model.entity.UserEntity;
import com.silva.securityservice.repository.RoleRepository;
import com.silva.securityservice.repository.UserRepository;
import com.silva.securityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponse createUser(UserCreationRequest request) throws RoleNotFoundException, UserFoundException {

        // TODO : guardar guest en una variable
        RoleEntity role = roleRepository.findByName("GUEST").orElseThrow( () -> new RoleNotFoundException("Rol no encontrado"));

        if(userRepository.findByUsernameOrEmail(request.username(), request.email()).isPresent()){
            throw new UserFoundException("el nombre de usuario o correo ya existe");
        }


        UserEntity newUser = new UserEntity();
        newUser.setEmail(request.email());
        newUser.setUsername(request.username());
        newUser.setPasswordHash(passwordEncoder.encode(request.password()));
        newUser.setStatus(UserEntity.Status.PENDING); // O PENDING si requiere verificación

        newUser.getRoles().add(role);

        UserEntity savedUser = userRepository.save(newUser);

        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .build();
    }
}
