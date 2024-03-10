package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.dto.ReminderMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderEmitter {
    private final KafkaTemplate<String, ReminderMessage> kafkaTemplate;
    public void emitReminder(ReminderMessage reminder) {
        kafkaTemplate.sendDefault(reminder);
    }
}
