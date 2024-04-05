package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

import java.util.List;

public record ScheduledVaccinationPostRequest(
        Long id,
        @Nonnull Long vaccineId,
        @Nonnull String dateTime,
        Integer doseNumber,
        List<ReminderRequest> reminders
){

}
