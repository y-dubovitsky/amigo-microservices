package ru.ydubovitsky.fraud.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FraudCheckResponse {

    public boolean isFraudulentCustomer;

}
