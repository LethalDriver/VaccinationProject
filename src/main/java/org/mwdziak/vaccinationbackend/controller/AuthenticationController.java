package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.RegistrationRequest;
import org.mwdziak.vaccinationbackend.service.AuthenticationService;
import org.mwdziak.vaccinationbackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.mwdziak.vaccinationbackend.dto.AuthenticationRequest;
import org.mwdziak.vaccinationbackend.dto.AuthenticationResponse;
import org.mwdziak.vaccinationbackend.dto.TokensDTO;


@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/auth/register")
    public AuthenticationResponse registerUser(@RequestBody RegistrationRequest request) {
        userService.registerUser(request);
        return authenticationService.authenticate(new AuthenticationRequest(request.getEmail(), request.getPassword()));
    }

    @PostMapping("/auth/login")
    public AuthenticationResponse loginUser(@RequestBody AuthenticationRequest user) {
        return authenticationService.authenticate(user);
    }
    @PostMapping("/auth/refresh")
    public AuthenticationResponse refreshToken(@RequestBody TokensDTO request) {
        return authenticationService.refresh(request);
    }

    @PostMapping("/auth/logout")
    public void logout(@RequestBody TokensDTO request) {
        authenticationService.logout(request);
    }

}
