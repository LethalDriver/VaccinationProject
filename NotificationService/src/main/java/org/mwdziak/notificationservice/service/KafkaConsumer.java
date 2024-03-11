package org.mwdziak.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.notificationservice.dto.ReminderMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final NotificationSender notificationSender;
    @KafkaListener(topics = "reminders")
    public void getNotificationsToSend(ReminderMessage reminderMessage){
        notificationSender.sendNotification(reminderMessage.getReminderTitle(), reminderMessage.getReminderMessage(), reminderMessage.getUsersToken());
    }
}
