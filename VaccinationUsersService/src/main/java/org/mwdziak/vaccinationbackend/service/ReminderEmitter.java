package org.mwdziak.vaccinationbackend.service;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mwdziak.vaccinationbackend.dto.reminder.ReminderMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReminderEmitter {
    private final SqsTemplate sqsTemplate;
    public void emitReminder(ReminderMessage reminder) {
        var result = sqsTemplate.send("Reminders", reminder);
        log.info(result.toString());
    }
}
