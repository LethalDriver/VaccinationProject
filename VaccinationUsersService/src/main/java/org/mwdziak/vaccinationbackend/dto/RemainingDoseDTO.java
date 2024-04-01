package org.mwdziak.vaccinationbackend.dto;

public record RemainingDoseDTO(
        Long id,
        Integer doseNumber,
        Integer daysBeforePreviousDose
) {
}
