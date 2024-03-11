package org.mwdziak.notificationservice.service;


import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationSender {
    private final FirebaseMessaging firebaseMessaging;
    private final Logger logger = LoggerFactory.getLogger(NotificationSender.class);
    public void sendNotification(String title, String content, String token) {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(content)
                        .build())
                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600 * 1000)
                        .build())
                .setToken(token)
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            logger.error("Error while sending notification", e);
        }
    }
}
