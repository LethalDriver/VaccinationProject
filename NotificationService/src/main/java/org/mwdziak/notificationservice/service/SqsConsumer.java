package org.mwdziak.notificationservice.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.mwdziak.notificationservice.dto.ReminderMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SqsConsumer {
    private final NotificationSender notificationSender;
    @SqsListener("Reminders")
    public void getNotificationsToSend(ReminderMessage reminderMessage){
        notificationSender.sendNotification(reminderMessage.getReminderTitle(), reminderMessage.getReminderMessage(), reminderMessage.getUsersToken());
    }
}
