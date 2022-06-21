package ru.ydubovitsky.notification.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ydubovitsky.notification.entity.Notification;
import ru.ydubovitsky.notification.payload.NotificationRequest;
import ru.ydubovitsky.notification.service.NotificationService;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/add")
    public Notification addNotification(NotificationRequest notificationRequest) {
        return notificationService.addNotificationToQueue(notificationRequest);
    }

}
