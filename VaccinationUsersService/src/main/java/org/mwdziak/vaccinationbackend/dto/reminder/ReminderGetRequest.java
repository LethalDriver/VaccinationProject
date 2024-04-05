package org.mwdziak.vaccinationbackend.dto.reminder;

import jakarta.annotation.Nonnull;

public record ReminderGetRequest(
        @Nonnull Long id,
        @Nonnull String dateTime
) {
}
