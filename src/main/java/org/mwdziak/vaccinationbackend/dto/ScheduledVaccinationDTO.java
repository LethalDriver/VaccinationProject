package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

public record ScheduledVaccinationDTO(
        Long id,
        @Nonnull VaccineDTO vaccine,
        @Nonnull String dateTime,
        String nextDoseDateTime,
        String reminderDateTime
){

}
