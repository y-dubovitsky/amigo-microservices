package ru.ydubovitsky.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    public Integer toCustomerId;
    public String toCustomerEmail;
    public String message;

}
