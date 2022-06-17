package ru.ydubovitsky.fraud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.fraud.model.FraudCheckHistory;
import ru.ydubovitsky.fraud.repository.FraudCheckHistoryRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckHistoryService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return false;
    }

}
