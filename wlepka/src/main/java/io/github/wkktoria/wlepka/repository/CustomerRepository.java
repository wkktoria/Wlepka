package io.github.wkktoria.wlepka.repository;

import io.github.wkktoria.wlepka.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
