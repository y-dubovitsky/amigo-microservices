package ru.ydubovitsky.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ydubovitsky.customer.model.Customer;
import ru.ydubovitsky.customer.payload.CustomerRegistrationRequest;
import ru.ydubovitsky.customer.payload.FraudCheckResponse;
import ru.ydubovitsky.customer.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.getFirstName())
                .lastName(customerRegistrationRequest.getLastName())
                .email(customerRegistrationRequest.getEmail())
                .build();

        customerRepository.saveAndFlush(customer); //FIXME how it is working? saveAndFlush??

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse.isFraudulentCustomer()) {
            throw new IllegalStateException("This customer is fraudster!");
        }

    }
}
