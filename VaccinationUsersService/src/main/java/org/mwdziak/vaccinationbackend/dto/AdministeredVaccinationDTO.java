package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

public record AdministeredVaccinationDTO(
        Long id,
        @Nonnull Long vaccineId,
        @Nonnull String dateTime
) {
}
