package io.github.wkktoria.wlepka.repository;

import io.github.wkktoria.wlepka.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(final String email);

    boolean existsByMobileNumber(final String mobileNumber);

    Optional<Customer> findByEmail(final String email);

    List<Customer> findAllByEmailOrMobileNumber(final String email, final String mobileNumber);

}
