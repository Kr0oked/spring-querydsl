package de.bobek.spring.querydsl.customer;

import java.time.Instant;
import java.util.List;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static de.bobek.spring.querydsl.customer.QCustomer.customer;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    @NonNull
    private final CustomerRepository customerRepository;

    @PostConstruct
    @Transactional
    public void init() {
        var customer = new Customer()
                .setName("John Doe")
                .setTimestamp(Instant.now());

        customer = customerRepository.save(customer);

        log.info("Saved {}", customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> searchCustomersByName(String value) {
        var predicate = customer.name.contains(value);
        return StreamSupport.stream(customerRepository.findAll(predicate).spliterator(), false)
                .map(this::mapToDto)
                .collect(toList());
    }

    private CustomerDto mapToDto(Customer entity) {
        return new CustomerDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setTimestamp(entity.getTimestamp());
    }
}
