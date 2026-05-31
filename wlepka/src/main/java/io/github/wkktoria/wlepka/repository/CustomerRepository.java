package io.github.wkktoria.wlepka.repository;

import io.github.wkktoria.wlepka.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailOrMobileNumber(final String email, final String mobileNumber);

}
