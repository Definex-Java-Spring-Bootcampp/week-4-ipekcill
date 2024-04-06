package com.patika.notificationservice.strategy.exception;

public class NotFoundNotificationStrategy extends RuntimeException {
    public NotFoundNotificationStrategy(String message) {
        super(message);
    }
}
