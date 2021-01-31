package de.bobek.spring.querydsl.customer;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CustomerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void searchCustomersByName() {
        var response = restTemplate.getForObject("/customer/search/Doe", CustomerDto[].class);

        assertThat(response)
                .extracting(CustomerDto::getName)
                .containsOnly("John Doe");
    }
}
