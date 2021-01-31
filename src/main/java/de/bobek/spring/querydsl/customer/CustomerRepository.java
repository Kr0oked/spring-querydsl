package de.bobek.spring.querydsl.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends
        JpaRepository<Customer, Long>,
        QuerydslPredicateExecutor<Customer> {
}
