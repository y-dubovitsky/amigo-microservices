package ru.ydubovitsky.customer.payload;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class FraudCheckResponse {

    public boolean isFraudulentCustomer;

}
