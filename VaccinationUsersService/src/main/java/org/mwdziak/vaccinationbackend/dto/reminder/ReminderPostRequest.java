package org.mwdziak.vaccinationbackend.dto.reminder;

import jakarta.annotation.Nonnull;
public record ReminderPostRequest(@Nonnull String dateTime) {
}
