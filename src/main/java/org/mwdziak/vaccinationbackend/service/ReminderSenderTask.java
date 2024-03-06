package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
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
