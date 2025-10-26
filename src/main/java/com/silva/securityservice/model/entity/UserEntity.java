package com.silva.securityservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_users_email", columnNames = "email"),
                @UniqueConstraint(name = "uq_users_username", columnNames = "username")
        })
public class UserEntity {

        @Id
        @GeneratedValue
        private UUID id;

        @Column(nullable = false, length = 255)
        private String email;

        @Column(length = 64, unique = true)
        private String username;

        @Column(name = "password_hash", nullable = false, columnDefinition = "TEXT")
        private String passwordHash;

        @Column(name = "password_algo", nullable = false, length = 32)
        private String passwordAlgo = "bcrypt";

        @Column(name = "is_email_verified", nullable = false)
        private boolean emailVerified = false;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, length = 16)
        private Status status = Status.PENDING;

        @Column(name = "must_change_password", nullable = false)
        private boolean mustChangePassword = false;

        @Column(name = "temp_password_expires_at")
        private Instant tempPasswordExpiresAt;

        @Column(name = "failed_attempts", nullable = false)
        private int failedAttempts = 0;

        @Column(name = "locked_until")
        private Instant lockedUntil;

        @Column(name = "last_login_at")
        private Instant lastLoginAt;

        @CreatedDate
        @Column(name = "created_at", nullable = false, updatable = false)
        private Instant createdAt = Instant.now();

        @LastModifiedDate
        @Column(name = "updated_at", nullable = false)
        private Instant updatedAt = Instant.now();

        // Enum para reflejar el constraint de status
        public enum Status {
                ACTIVE,
                BLOCKED,
                PENDING
        }



        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
        )
        private Set<RoleEntity> roles = new HashSet<>();
}
