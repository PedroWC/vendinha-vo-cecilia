package com.vendinha.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotNull(message = "O nome de usuário não pode ser nulo")
    private String username;

    @NotNull(message = "O email não pode ser nulo")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "A senha não pode ser nula ou vazia")
    private String password;
}