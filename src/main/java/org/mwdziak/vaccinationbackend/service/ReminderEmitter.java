package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderEmitter {
    private final KafkaTemplate<String, Reminder> kafkaTemplate;
    public void emitReminder(Reminder reminder) {
        kafkaTemplate.sendDefault(reminder);
    }
}
