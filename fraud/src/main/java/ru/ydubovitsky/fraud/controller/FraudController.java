package ru.ydubovitsky.fraud.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.clients.fraud.FraudCheckResponse;
import ru.ydubovitsky.fraud.service.FraudCheckHistoryService;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean fraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);

        return FraudCheckResponse.builder()
                .isFraudulentCustomer(fraudulentCustomer)
                .build();
    }

}
