package org.mwdziak.vaccinationbackend.scheduling;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.service.ReminderEmitter;
import org.springframework.stereotype.Component;


@AllArgsConstructor
public class ReminderSenderTask implements Runnable {
    private final ReminderEmitter reminderEmitter;
    private Reminder reminder;
    @Override
    public void run() {
        reminderEmitter.emitReminder(reminder);
    }
}
