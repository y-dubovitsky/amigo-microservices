package ru.ydubovitsky.notification.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.clients.notification.NotificationRequest;
import ru.ydubovitsky.clients.notification.NotificationResponse;
import ru.ydubovitsky.notification.entity.Notification;
import ru.ydubovitsky.notification.service.NotificationService;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/add")
    public NotificationResponse addNotification(@RequestBody NotificationRequest notificationRequest) {
        Notification notification = notificationService.addNotificationToQueue(notificationRequest);

        return NotificationResponse.builder()
                .toCustomerId(notification.getToCustomerId())
                .message(notification.getMessage())
                .build();
    }

}
