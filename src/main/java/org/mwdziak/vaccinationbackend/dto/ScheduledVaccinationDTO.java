package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

import java.util.List;

public record ScheduledVaccinationDTO(
        Long id,
        @Nonnull VaccineDTO vaccine,
        @Nonnull String dateTime,
        String nextDoseDateTime,
        List<ReminderDTO> reminders
){

}
