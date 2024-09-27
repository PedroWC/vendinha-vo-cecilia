package com.vendinha.security;

import com.vendinha.util.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        // Secret usado para os testes
        String secret = "mytestsecretkeythatissufficientlylong";
        jwtUtil = new JwtUtil(secret);
    }

    @Test
    void testGenerateToken() {
        // Criando um UserDetails mockado
        UserDetails userDetails = User.withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        // Gerar o token JWT
        String token = jwtUtil.generateToken(userDetails);

        // Verificar se o token não é nulo
        assertNotNull(token);

        // Verificar se o token contém o username correto
        String username = jwtUtil.extractUsername(token);
        assertEquals("testuser", username);
    }

    @Test
    void testValidateTokenSuccess() {
        // Criando um UserDetails mockado
        UserDetails userDetails = User.withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        // Gerar o token JWT
        String token = jwtUtil.generateToken(userDetails);

        // Validar o token
        assertTrue(jwtUtil.validateToken(token, userDetails));
    }

    @Test
    void testValidateTokenFailure() {
        // Criando um UserDetails mockado
        UserDetails userDetails = User.withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        // Gerar o token JWT
        String token = jwtUtil.generateToken(userDetails);

        // Criar outro UserDetails com um nome de usuário diferente
        UserDetails anotherUserDetails = User.withUsername("otheruser")
                .password("password")
                .roles("USER")
                .build();

        // Validar o token com um usuário diferente
        assertFalse(jwtUtil.validateToken(token, anotherUserDetails));
    }

    @Test
    void testExtractUsername() {
        // Criando um UserDetails mockado
        UserDetails userDetails = User.withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        // Gerar o token JWT
        String token = jwtUtil.generateToken(userDetails);

        // Extrair o username do token
        String username = jwtUtil.extractUsername(token);

        // Verificar se o username extraído é correto
        assertEquals("testuser", username);
    }
}