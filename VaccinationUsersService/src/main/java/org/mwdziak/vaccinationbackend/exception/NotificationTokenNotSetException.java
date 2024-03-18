package org.mwdziak.vaccinationbackend.exception;

public class NotificationTokenNotSetException extends RuntimeException {
    public NotificationTokenNotSetException(String userHasNoNotificationToken) {
    }
}
