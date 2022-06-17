package ru.ydubovitsky.fraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.fraud.model.FraudCheckHistory;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
