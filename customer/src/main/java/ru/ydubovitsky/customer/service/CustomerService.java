package ru.ydubovitsky.customer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.clients.fraud.FraudCheckResponse;
import ru.ydubovitsky.clients.fraud.FraudClient;
import ru.ydubovitsky.clients.notification.NotificationClient;
import ru.ydubovitsky.clients.notification.NotificationRequest;
import ru.ydubovitsky.clients.notification.NotificationResponse;
import ru.ydubovitsky.customer.model.Customer;
import ru.ydubovitsky.customer.payload.CustomerRegistrationRequest;
import ru.ydubovitsky.customer.repository.CustomerRepository;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

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
        NotificationResponse notificationResponse =
                notificationClient.addNotification(
                        NotificationRequest.builder()
                        .message("You had registered!")
                        .toCustomerEmail(customer.getEmail())
                        .toCustomerId(customer.getId())
                        .build());

        log.info(notificationResponse.toString()); //TODO?
    }
}
