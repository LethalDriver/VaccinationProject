package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

import java.util.List;

public record VaccineDTO(
        Long id,
        @Nonnull String name,
        Integer recommendedAgeMonthsLowerBound,
        Integer recommendedAgeMonthsUpperBound,
        List<DosesDTO> doses
) {
}
