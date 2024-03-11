package org.mwdziak.notificationservice.service;

import org.mwdziak.notificationservice.dto.ReminderMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "vaccination-reminder")
    public void getNotificationsToSend(ReminderMessage reminderMessage){
        //TODO
    }
}
