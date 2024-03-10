package org.mwdziak.vaccinationbackend.service;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class JwtServiceTest {

    private JwtService jwtService;
    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        jwtService = new JwtService();
        when(userDetails.getUsername()).thenReturn("testUser");
    }

    @Test
    public void testGenerateToken() {
        String token = jwtService.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    public void testGenerateRefreshToken() {
        String token = jwtService.generateRefreshToken(userDetails);
        assertNotNull(token);
    }

    @Test
    public void testIsTokenValid() {
        String token = jwtService.generateToken(userDetails);
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    public void testIsTokenExpired() {
        String token = jwtService.generateToken(userDetails, -1000L);
        assertThrows(ExpiredJwtException.class, () -> jwtService.isTokenValid(token, userDetails));
    }

    @Test
    public void testExtractUsername() {
        String token = jwtService.generateToken(userDetails);
        assertEquals("testUser", jwtService.extractUsername(token));
    }

    @Test
    public void testExtractExpiration() {
        String token = jwtService.generateToken(userDetails);
        Date expiration = jwtService.extractExpiration(token);
        assertNotNull(expiration);
    }
}