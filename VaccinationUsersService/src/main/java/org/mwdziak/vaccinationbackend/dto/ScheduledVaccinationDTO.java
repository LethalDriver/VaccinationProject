package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

import java.util.List;

public record ScheduledVaccinationDTO(
        Long id,
        @Nonnull VaccineDTO vaccine,
        @Nonnull String dateTime,
        Integer doseNumber,
        List<ReminderRequest> reminders
){

}
