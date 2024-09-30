package com.vendinha.controller;

import com.vendinha.dto.LoginDTO;
import com.vendinha.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Permite CORS para este controlador
public class AuthController {

    private final AuthService authService;

    // Injeção de dependências
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Autentica o usuário e retorna um token de acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            String token = authService.authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());
            // Se a autenticação for bem-sucedida, retorna o token de autenticação
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            // Caso contrário, retorna uma mensagem de erro
            return ResponseEntity.status(401).body("Email ou senha inválidos.");
        }
    }
}
