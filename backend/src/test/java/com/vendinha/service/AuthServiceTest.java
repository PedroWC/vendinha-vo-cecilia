package com.vendinha.service;

import com.vendinha.model.User;
import com.vendinha.repository.UserRepository;
import com.vendinha.util.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticateUser_validCredentials_shouldReturnToken() throws Exception {
        // Cenário positivo: Credenciais válidas retornam token
        User mockUser = new User();
        mockUser.setEmail("user@example.com");
        mockUser.setPassword("hashedPassword");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches("password", mockUser.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(any())).thenReturn("mockToken");

        // Teste da autenticação com credenciais válidas
        String token = authService.authenticateUser("user@example.com", "password");

        // Validação do retorno do token
        assertEquals("mockToken", token);

        // Verificação das interações com o repositório e métodos de geração de token
        verify(userRepository, times(1)).findByEmail("user@example.com");
        verify(passwordEncoder, times(1)).matches("password", mockUser.getPassword());
        verify(jwtUtil, times(1)).generateToken(any());
    }

    @Test
    void authenticateUser_invalidPassword_shouldThrowException() {
        // Cenário negativo: Senha inválida gera exceção
        User mockUser = new User();
        mockUser.setEmail("user@example.com");
        mockUser.setPassword("hashedPassword");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches("wrongPassword", mockUser.getPassword())).thenReturn(false);

        // Verificação da exceção ao fornecer senha inválida
        Exception exception = assertThrows(Exception.class, () ->
                authService.authenticateUser("user@example.com", "wrongPassword")
        );

        // Verificação da mensagem da exceção
        assertEquals("Senha inválida", exception.getMessage());

        // Verificação das interações com o repositório e a comparação da senha
        verify(userRepository, times(1)).findByEmail("user@example.com");
        verify(passwordEncoder, times(1)).matches("wrongPassword", mockUser.getPassword());
        verify(jwtUtil, never()).generateToken(any()); // Token não deve ser gerado
    }

    @Test
    void authenticateUser_userNotFound_shouldThrowException() {
        // Cenário negativo: Usuário não encontrado gera exceção
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Verificação da exceção ao tentar autenticar um usuário inexistente
        Exception exception = assertThrows(Exception.class, () ->
                authService.authenticateUser("nonexistent@example.com", "password")
        );

        // Verificação da mensagem da exceção
        assertEquals("Usuário não encontrado", exception.getMessage());

        // Verificação das interações com o repositório e a ausência de token
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(jwtUtil, never()).generateToken(any());
    }
}
