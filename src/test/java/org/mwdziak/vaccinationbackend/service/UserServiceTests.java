package org.mwdziak.vaccinationbackend.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mwdziak.vaccinationbackend.domain.User;
import org.mwdziak.vaccinationbackend.dto.RegistrationRequest;
import org.mwdziak.vaccinationbackend.exception.UserAlreadyExistsException;
import org.mwdziak.vaccinationbackend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Mock
    private UserDetails userDetails;

    @Mock
    private Authentication authentication;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private SecurityContext securityContext;


    @BeforeEach
    public void setUp() {

    }

    @Test
    public void shouldGetCurrentUsersEmail() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("test@test.com");

        String email = userService.getCurrentUserEmail();

        assertEquals("test@test.com", email);
    }

    @Test
    public void shouldThrowUserAlreadyExistsException() {
        var request = RegistrationRequest.builder()
                .email("test")
                .build();
        when(userRepository.existsByEmail("test")).thenReturn(true);
        assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(request));
    }

    @Test
    public void shouldRegisterUser() {
        var request = RegistrationRequest.builder()
                .email("test")
                .password("password")
                .firstName("first")
                .lastName("last")
                .dateOfBirth("1990-01-01")
                .build();
        when(userRepository.existsByEmail("test")).thenReturn(false);
        when(userRepository.save(User.builder()
                .email("test")
                .password("password")
                .firstName("first")
                .lastName("last")
                .dateOfBirth(LocalDate.parse("1990-01-01"))
                .role(User.Role.USER)
                .build()))
                .thenReturn(User.builder()
                        .email("test")
                        .password("password")
                        .firstName("first")
                        .lastName("last")
                        .dateOfBirth(LocalDate.parse("1990-01-01"))
                        .role(User.Role.USER)
                        .build());
        when (encoder.encode("password")).thenReturn("password");
        User user = userService.registerUser(request);
        assertNotNull(user);
    }
}