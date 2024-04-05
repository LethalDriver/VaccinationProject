package org.mwdziak.vaccinationbackend.dto.vaccination;

import jakarta.annotation.Nonnull;
import org.mwdziak.vaccinationbackend.dto.VaccineDTO;
import org.mwdziak.vaccinationbackend.dto.reminder.ReminderRequest;

import java.util.List;

public record ScheduledVaccinationGetRequest(
        Long id,
        @Nonnull VaccineDTO vaccine,
        @Nonnull String dateTime,
        Integer doseNumber,
        List<ReminderRequest> reminders
) {
}
