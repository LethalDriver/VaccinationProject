package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;
public record ReminderDTO(Long id, @Nonnull String dateTime) {
}
