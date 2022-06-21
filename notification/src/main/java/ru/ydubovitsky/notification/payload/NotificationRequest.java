package ru.ydubovitsky.notification.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {
    Integer toCustomerId;
    String toCustomerEmail;
    String message;
}

