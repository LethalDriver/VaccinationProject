package org.mwdziak.vaccinationbackend.scheduling;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.service.ReminderEmitter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReminderSenderTask implements Runnable {
    private final ReminderEmitter reminderEmitter;
    @Override
    public void run() {
        reminderEmitter.emitReminder();
    }
}
