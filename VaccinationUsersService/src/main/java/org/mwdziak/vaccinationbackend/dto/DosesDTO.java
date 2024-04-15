package org.mwdziak.vaccinationbackend.dto;

public record DosesDTO(
        Long id,
        Integer doseNumber,
        Integer daysAfterPreviousDose
) {
}
