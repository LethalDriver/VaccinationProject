package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.RegistrationRequest;
import org.mwdziak.vaccinationbackend.service.AuthenticationService;
import org.mwdziak.vaccinationbackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.mwdziak.vaccinationbackend.dto.AuthenticationRequest;
import org.mwdziak.vaccinationbackend.dto.AuthenticationResponse;
import org.mwdziak.vaccinationbackend.dto.TokensDTO;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public AuthenticationResponse registerUser(@RequestBody RegistrationRequest request) {
        userService.registerUser(request);
        return authenticationService.authenticate(new AuthenticationRequest(request.getEmail(), request.getPassword()));
    }

    @PostMapping("/login")
    public AuthenticationResponse loginUser(@RequestBody AuthenticationRequest user) {
        return authenticationService.authenticate(user);
    }
    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestBody TokensDTO request) {
        return authenticationService.refresh(request);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody TokensDTO request) {
        authenticationService.logout(request);
    }

}
