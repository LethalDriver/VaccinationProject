package org.mwdziak.vaccinationbackend.scheduling;

import lombok.AllArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.reminder.ReminderMessage;
import org.mwdziak.vaccinationbackend.service.ReminderEmitter;


@AllArgsConstructor
public class ReminderSenderTask implements Runnable {
    private final ReminderEmitter reminderEmitter;
    private ReminderMessage reminder;
    @Override
    public void run() {
        reminderEmitter.emitReminder(reminder);
    }
}
