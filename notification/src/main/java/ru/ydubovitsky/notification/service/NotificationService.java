package ru.ydubovitsky.notification.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.notification.entity.Notification;
import ru.ydubovitsky.notification.payload.NotificationRequest;
import ru.ydubovitsky.notification.repository.NotificationRepository;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification addNotificationToQueue(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .message(notificationRequest.getMessage())
                .toCustomerEmail(notificationRequest.getToCustomerEmail())
                .toCustomerId(notificationRequest.getToCustomerId())
                .build();

        log.info("Notification added");
        return notification;
    }

}
