package com.silva.securityservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserCreationRequest(
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El formato del email es inválido")
        String email,

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        @Size(min = 3, max = 64, message = "El nombre de usuario debe tener entre 3 y 64 caracteres")
        String username,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password
) {
}
