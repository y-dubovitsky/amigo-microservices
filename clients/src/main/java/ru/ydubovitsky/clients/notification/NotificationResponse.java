package ru.ydubovitsky.clients.notification;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {

    public Integer toCustomerId;
    public String toCustomerEmail;
    public String message;

}
