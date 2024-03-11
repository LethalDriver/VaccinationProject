package org.mwdziak.notificationservice.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReminderMessage {
    @Nonnull
    private String reminderTitle;
    @Nonnull
    private String reminderMessage;
    @Nonnull
    private String usersToken;
}
