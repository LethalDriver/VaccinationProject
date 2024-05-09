package org.mwdziak.vaccinationbackend.dto;

public record UserDetailsDTO(
        Long id,
        String email,
        String firstName,
        String lastName,
        String dateOfBirth,
        String role
) {
}
