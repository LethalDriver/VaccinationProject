package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;
public record ReminderRequest(@Nonnull String dateTime) {
}
