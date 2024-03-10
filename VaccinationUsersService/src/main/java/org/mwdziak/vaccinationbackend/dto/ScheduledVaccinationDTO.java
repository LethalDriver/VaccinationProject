package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

import java.util.List;

public record ScheduledVaccinationDTO(
        Long id,
        @Nonnull Long vaccineId,
        @Nonnull String dateTime,
        String nextDoseDateTime,
        List<ReminderRequest> reminders
){

}
