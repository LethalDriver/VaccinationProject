package org.mwdziak.vaccinationbackend.service;

import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReminderEmitter {
    private KafkaTemplate<String, Reminder> kafkaTemplate;
    public void emitReminder(Reminder reminder) {
        kafkaTemplate.sendDefault(reminder);
    }
}
