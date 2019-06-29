package de.bobek.spring.querydsl.customer;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;
import java.util.stream.StreamSupport;

import static de.bobek.spring.querydsl.customer.QCustomer.customer;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    @PostConstruct
    @Transactional
    public void init() {
        Customer customer = new Customer()
                .setName("John Doe")
                .setTimestamp(Instant.now());

        Customer savedCustomer = customerRepository.save(customer);

        log.info("Saved {}", savedCustomer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> searchCustomersByName(String value) {
        BooleanExpression predicate = customer.name.contains(value);
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
