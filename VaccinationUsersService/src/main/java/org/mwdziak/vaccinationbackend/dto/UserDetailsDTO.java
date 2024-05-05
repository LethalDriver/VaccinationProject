package org.mwdziak.vaccinationbackend.dto;

public record UserDetailsDTO(
        String email,
        String firstName,
        String lastName,
        String dateOfBirth,
        String role
) {
}
