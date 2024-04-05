package org.mwdziak.vaccinationbackend.dto.reminder;

import jakarta.annotation.Nonnull;
public record ReminderRequest(@Nonnull String dateTime) {
}
