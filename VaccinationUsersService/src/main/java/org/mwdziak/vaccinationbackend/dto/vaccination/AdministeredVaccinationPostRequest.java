package org.mwdziak.vaccinationbackend.dto.vaccination;

import jakarta.annotation.Nonnull;

public record AdministeredVaccinationPostRequest(
        Long id,
        @Nonnull Long vaccineId,
        @Nonnull String dateTime,
        Integer doseNumber
) {
}
