package de.bobek.spring.querydsl.customer;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    @NonNull
    private final CustomerService customerService;

    @GetMapping("/customer/search/{value}")
    public List<CustomerDto> searchCustomers(@PathVariable String value) {
        return customerService.searchCustomersByName(value);
    }
}
