package ru.ydubovitsky.customer.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    Integer toCustomerId;
    String toCustomerEmail;
    String message;
}
