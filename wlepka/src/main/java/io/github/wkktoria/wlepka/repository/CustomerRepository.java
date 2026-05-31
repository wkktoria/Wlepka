package io.github.wkktoria.wlepka.repository;

import io.github.wkktoria.wlepka.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByEmailOrMobileNumber(final String email, final String mobileNumber);

}
