package ru.ydubovitsky.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
