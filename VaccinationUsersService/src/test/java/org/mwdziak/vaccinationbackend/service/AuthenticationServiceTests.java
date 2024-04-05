package org.mwdziak.vaccinationbackend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mwdziak.vaccinationbackend.domain.BlacklistedToken;
import org.mwdziak.vaccinationbackend.domain.User;
import org.mwdziak.vaccinationbackend.dto.auth.AuthenticationRequest;
import org.mwdziak.vaccinationbackend.dto.auth.TokensDTO;
import org.mwdziak.vaccinationbackend.repository.BlacklistedTokenRepository;
import org.mwdziak.vaccinationbackend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private BlacklistedTokenRepository blacklistedTokenRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    private User user;

    @Test
    public void testAuthenticate() {
        user = new User();
        user.setEmail("test@test.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("test@test.com");
        request.setPassword("password");

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()))).thenReturn(null);
        when(jwtService.generateToken(user)).thenReturn("jwtToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("refreshToken");
        when(jwtService.extractExpiration(anyString())).thenReturn(new Date());


        var response = authenticationService.authenticate(request);

        assertNotNull(response);
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
    }

    @Test
    public void testLogout() {
        TokensDTO tokensDTO = new TokensDTO();
        tokensDTO.setToken("token");
        tokensDTO.setRefreshToken("refreshToken");

        authenticationService.logout(tokensDTO);

        verify(blacklistedTokenRepository, times(1)).save(new BlacklistedToken(tokensDTO.getToken()));
        verify(blacklistedTokenRepository, times(1)).save(new BlacklistedToken(tokensDTO.getRefreshToken()));
    }

    @Test
    public void testRefresh() {
        user = new User();
        user.setEmail("test@test.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        TokensDTO tokensDTO = new TokensDTO();
        tokensDTO.setToken("token");
        tokensDTO.setRefreshToken("refreshToken");


        when(jwtService.extractUsername(tokensDTO.getRefreshToken())).thenReturn("test@test.com");
        when(blacklistedTokenRepository.existsByToken(tokensDTO.getRefreshToken())).thenReturn(false);
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("newToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("newRefreshToken");
        when(jwtService.extractExpiration(anyString())).thenReturn(new Date());

        var response = authenticationService.refresh(tokensDTO);

        assertNotNull(response);
        assertEquals("newToken", response.getToken());
        assertEquals("newRefreshToken", response.getRefreshToken());
    }


    // Add more tests for refresh and logout methods
}