package org.mwdziak.vaccinationbackend.service;


import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.BlacklistedToken;

import org.mwdziak.vaccinationbackend.dto.AuthenticationRequest;
import org.mwdziak.vaccinationbackend.dto.AuthenticationResponse;
import org.mwdziak.vaccinationbackend.dto.TokensDTO;
import org.mwdziak.vaccinationbackend.exception.TokenBlacklistedException;
import org.mwdziak.vaccinationbackend.repository.BlacklistedTokenRepository;
import org.mwdziak.vaccinationbackend.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var userOptional = repository.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect password");
        }

        var user = userOptional.get();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        var expirationDate = jwtService.extractExpiration(refreshToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .expirationDate(expirationDate.toString())
                .build();
    }

    public AuthenticationResponse refresh(TokensDTO request) {
        var email = jwtService.extractUsername(request.getRefreshToken());
        var isTokenBlacklisted = blacklistedTokenRepository.existsByToken(request.getRefreshToken());
        if (isTokenBlacklisted) {
            throw new TokenBlacklistedException("Token is blacklisted");
        }
        blacklistedTokenRepository.save(new BlacklistedToken(request.getToken()));
        blacklistedTokenRepository.save(new BlacklistedToken(request.getRefreshToken()));
        var user = repository.findByEmail(email).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        var expirationDate = jwtService.extractExpiration(refreshToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .expirationDate(expirationDate.toString())
                .build();

    }

    public void logout(TokensDTO request) {
        blacklistedTokenRepository.save(new BlacklistedToken(request.getToken()));
        blacklistedTokenRepository.save(new BlacklistedToken(request.getRefreshToken()));
    }

}
