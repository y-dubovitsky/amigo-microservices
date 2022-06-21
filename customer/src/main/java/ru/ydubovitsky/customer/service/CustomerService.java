package ru.ydubovitsky.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ydubovitsky.clients.fraud.FraudCheckResponse;
import ru.ydubovitsky.clients.fraud.FraudClient;
import ru.ydubovitsky.customer.model.Customer;
import ru.ydubovitsky.customer.payload.CustomerRegistrationRequest;
import ru.ydubovitsky.customer.payload.NotificationRequest;
import ru.ydubovitsky.customer.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.getFirstName())
                .lastName(customerRegistrationRequest.getLastName())
                .email(customerRegistrationRequest.getEmail())
                .build();

        customerRepository.saveAndFlush(customer); //FIXME how it is working? saveAndFlush??

        //OpenFeign in action
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudulentCustomer()) {
            throw new IllegalStateException("This customer is fraudster!");
        }

        // Send notification!
        restTemplate.getForObject(
                "http://NOTIFICATION/api/v1/notification/add",
                NotificationRequest.class,
                NotificationRequest.builder()
                        .toCustomerEmail(customerRegistrationRequest.getEmail())
                        .toCustomerId(1)
                        .message("You had registered")
                        .build()
        );

    }
}
