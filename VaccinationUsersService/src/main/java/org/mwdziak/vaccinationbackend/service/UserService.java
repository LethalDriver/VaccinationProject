package org.mwdziak.vaccinationbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.User;
import org.mwdziak.vaccinationbackend.dto.NotificationTokenRequest;
import org.mwdziak.vaccinationbackend.dto.UserDetailsDTO;
import org.mwdziak.vaccinationbackend.dto.auth.RegistrationRequest;
import org.mwdziak.vaccinationbackend.exception.UserAlreadyExistsException;
import org.mwdziak.vaccinationbackend.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        }

        return null;
    }

    public User getCurrentUser() {
        return userRepository.findByEmail(getCurrentUserEmail()).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
    }

    public User registerUser(RegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
                .role(User.Role.USER)
                .build();

        return userRepository.save(user);
    }

    public void assignToken(NotificationTokenRequest token) {
        var user = getCurrentUser();
        user.setNotificationToken(token.token());
        userRepository.save(user);
    }

    public UserDetailsDTO getUserDetailsForCurrentUser() {
        var user = getCurrentUser();
        return new UserDetailsDTO(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth().toString(),
                user.getRole().name()
        );
    }

}
