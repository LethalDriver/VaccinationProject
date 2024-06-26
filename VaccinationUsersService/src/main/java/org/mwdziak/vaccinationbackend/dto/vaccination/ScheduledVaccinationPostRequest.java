package org.mwdziak.vaccinationbackend.dto.vaccination;

import jakarta.annotation.Nonnull;
import org.mwdziak.vaccinationbackend.dto.reminder.ReminderPostRequest;

import java.util.List;

public record ScheduledVaccinationPostRequest(
        @Nonnull Long vaccineId,
        @Nonnull String dateTime,
        Integer doseNumber,
        List<ReminderPostRequest> reminders
){

}
