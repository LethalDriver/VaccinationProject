package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ReminderMessage {
    @Nonnull
    private String reminderTitle;
    @Nonnull
    private String reminderMessage;
    @Nonnull
    private String usersToken;
}
