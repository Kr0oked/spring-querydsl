package de.bobek.spring.querydsl.customer;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class CustomerDto {

    private Long id;
    private String name;
    private Instant timestamp;
}
