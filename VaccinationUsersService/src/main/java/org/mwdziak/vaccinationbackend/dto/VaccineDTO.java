package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

public record VaccineDTO(
        Long id,
        @Nonnull String name,
        Integer recommendedAgeMonthsLowerBound,
        Integer recommendedAgeMonthsUpperBound,
        @Nonnull Integer doses,
        List<RemainingDoseDTO> remainingDoses
) {
}
