package ru.ydubovitsky.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
