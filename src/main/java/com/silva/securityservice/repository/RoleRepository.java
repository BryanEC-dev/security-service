package com.silva.securityservice.repository;

import com.silva.securityservice.model.entity.RoleEntity;
import com.silva.securityservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(String name);
}
