package com.vendinha.service;

import com.vendinha.dto.UserDTO;
import com.vendinha.model.User;
import com.vendinha.repository.UserRepository;
import com.vendinha.util.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByEmail_validEmail() {
        // Cenário positivo: Email válido retorna UserDetails
        User mockUser = new User();
        mockUser.setUsername("validUsername");
        mockUser.setEmail("user@example.com");
        mockUser.setPassword("password");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(mockUser));

        // Teste de carregamento do usuário por email
        var userDetails = userService.loadUserByEmail("user@example.com");

        // Verificação do retorno de UserDetails correto
        assertEquals("user@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());

        // Verificação das interações com o repositório
        verify(userRepository, times(1)).findByEmail("user@example.com");
    }

    @Test
    void loadUserByEmail_invalidEmail() {
        // Cenário negativo: Email inválido lança exceção
        when(userRepository.findByEmail("invalid@example.com")).thenReturn(Optional.empty());

        // Verificação da exceção ao tentar carregar um email inexistente
        assertThrows(UsernameNotFoundException.class, () ->
                userService.loadUserByEmail("invalid@example.com"));

        // Verificação da interação com o repositório
        verify(userRepository, times(1)).findByEmail("invalid@example.com");
    }

    @Test
    void getAllUsers() {
        // Cenário positivo: Retorno da lista de usuários
        User mockUser = new User();
        mockUser.setEmail("user@example.com");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(mockUser));
        when(userMapper.toDto(mockUser)).thenReturn(new UserDTO());

        // Teste da obtenção de todos os usuários
        List<UserDTO> users = userService.getAllUsers();

        // Verificação do tamanho da lista e das interações
        assertEquals(1, users.size());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).toDto(mockUser);
    }

    @Test
    void getUserById_validId() {
        // Cenário positivo: Retorno de um usuário por ID válido
        User mockUser = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userMapper.toDto(mockUser)).thenReturn(new UserDTO());

        // Teste de obtenção de usuário por ID
        UserDTO userDTO = userService.getUserById(1L);

        // Verificação do retorno de UserDTO
        assertNotNull(userDTO);
        verify(userRepository, times(1)).findById(1L);
        verify(userMapper, times(1)).toDto(mockUser);
    }

    @Test
    void getUserById_invalidId() {
        // Cenário negativo: ID inválido lança exceção
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificação da exceção ao tentar obter um usuário com ID inexistente
        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));

        // Verificação das interações
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void saveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("plainPassword");

        // Criar uma instância de User para ser retornada pelo mock do UserMapper
        User mockUserEntity = new User();
        mockUserEntity.setUsername("testUser");
        mockUserEntity.setEmail("test@example.com");
        mockUserEntity.setPassword("plainPassword");

        // Simular o comportamento do passwordEncoder
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        // Simular o mapeamento de DTO para entidade User
        when(userMapper.toEntity(userDTO)).thenReturn(mockUserEntity);

        // Executar o teste
        userService.saveUser(userDTO);

        // Verificar se o passwordEncoder foi chamado com a senha correta
        verify(passwordEncoder).encode("plainPassword");
        // Verificar se o userRepository salvou a entidade
        verify(userRepository).save(mockUserEntity);
    }

    @Test
    void saveUser_whenPasswordIsNull() {
        // Cenário: Criar um UserDTO com senha nula
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("validUsername");
        userDTO.setEmail("user@example.com");
        userDTO.setPassword(null); // Senha nula

        // Criar uma instância de User com senha nula
        User mockUserEntity = new User();
        mockUserEntity.setUsername("validUsername");
        mockUserEntity.setEmail("user@example.com");
        mockUserEntity.setPassword(null); // Senha nula

        // Simular mapeamento do DTO para a entidade User
        when(userMapper.toEntity(userDTO)).thenReturn(mockUserEntity);

        // Execução: Tentar salvar o usuário com senha nula e esperar uma exceção
        Exception exception = assertThrows(RuntimeException.class, () -> userService.saveUser(userDTO));

        // Verificação: A exceção deve ser de senha nula
        assertEquals("A senha não pode ser nula ou vazia", exception.getMessage());
    }

    @Test
    void saveUser_whenUsernameIsNull() {
        // Cenário: Criar um UserDTO com nome de usuário nulo
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("user@example.com");
        userDTO.setUsername(null); // Nome de usuário nulo
        userDTO.setPassword("validPassword");

        // Simular mapeamento do DTO para a entidade User com username nulo
        User user = new User();
        user.setUsername(null);
        when(userMapper.toEntity(userDTO)).thenReturn(user);

        // Simular comportamento do repositório
        when(userRepository.save(user)).thenThrow(new RuntimeException("Nome de usuário não pode ser nulo"));

        // Execução: Tentar salvar o usuário com nome de usuário nulo
        Exception exception = assertThrows(RuntimeException.class, () -> userService.saveUser(userDTO));

        // Verificação: A exceção deve ser de nome de usuário nulo
        assertEquals("Nome de usuário não pode ser nulo", exception.getMessage());
    }

    @Test
    void saveUser_whenEmailIsInvalid() {
        // Cenário: Criar um UserDTO com email inválido
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("validUsername");
        userDTO.setEmail("invalid-email"); // Email inválido
        userDTO.setPassword("validPassword");

        // Simular mapeamento do DTO para a entidade User
        User user = new User();
        user.setEmail("invalid-email");
        when(userMapper.toEntity(userDTO)).thenReturn(user);

        // Simular comportamento do repositório
        when(userRepository.save(user)).thenThrow(new RuntimeException("Email inválido"));

        // Execução: Tentar salvar o usuário com email inválido
        Exception exception = assertThrows(RuntimeException.class, () -> userService.saveUser(userDTO));

        // Verificação: A exceção deve ser de email inválido
        assertEquals("Email inválido", exception.getMessage());
    }

    @Test
    void updateUser_validId() {
        // Cenário positivo: Atualiza e retorna UserDTO
        UserDTO userDTO = new UserDTO();
        User mockUser = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        when(userMapper.toDto(mockUser)).thenReturn(userDTO);

        // Teste de atualização de usuário
        UserDTO updatedUserDTO = userService.updateUser(1L, userDTO);

        // Verificação do retorno do UserDTO atualizado
        assertNotNull(updatedUserDTO);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(mockUser);
        verify(userMapper, times(1)).toDto(mockUser);
    }

    @Test
    void updateUser_invalidId() {
        // Cenário negativo: ID inválido lança exceção
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificação da exceção ao tentar atualizar um usuário inexistente
        assertThrows(RuntimeException.class, () -> userService.updateUser(1L, new UserDTO()));

        // Verificação das interações
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).save(any());
    }

    @Test
    void deleteUser_validId() {
        // Cenário positivo: Deleta o usuário pelo ID válido
        when(userRepository.existsById(1L)).thenReturn(true);

        // Teste de exclusão de usuário
        userService.deleteUser(1L);

        // Verificação da exclusão e interações
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_invalidId() {
        // Cenário negativo: ID inválido lança exceção
        when(userRepository.existsById(1L)).thenReturn(false);

        // Verificação da exceção ao tentar excluir um usuário inexistente
        assertThrows(RuntimeException.class, () -> userService.deleteUser(1L));

        // Verificação das interações
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, never()).deleteById(1L);
    }
}