package io.github.wkktoria.wlepka.service.impl;

import io.github.wkktoria.wlepka.dto.request.RegisterRequestDto;
import io.github.wkktoria.wlepka.dto.response.RegisterResponseDto;
import io.github.wkktoria.wlepka.entity.Customer;
import io.github.wkktoria.wlepka.entity.Role;
import io.github.wkktoria.wlepka.repository.CustomerRepository;
import io.github.wkktoria.wlepka.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Override
    public RegisterResponseDto registerUser(final RegisterRequestDto registerRequestDto) {
        Map<String, String> errors = new HashMap<>();
        errors.putAll(validatePassword(registerRequestDto.password()));
        errors.putAll(validateEmailAndMobileNumber(registerRequestDto.email(),
                registerRequestDto.mobileNumber()));

        if (!errors.isEmpty()) {
            return RegisterResponseDto.builder()
                    .errors(errors)
                    .build();
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(registerRequestDto, customer);
        customer.setPasswordHash(passwordEncoder.encode(registerRequestDto.password()));

        Role role = new Role();
        role.setName("ROLE_USER");
        customer.setRoles(Set.of(role));

        customerRepository.save(customer);

        return RegisterResponseDto.builder()
                .message("Zarejestrowano pomyślnie!")
                .build();
    }

    private Map<String, String> validatePassword(final String password) {
        Map<String, String> errors = new HashMap<>();

        CompromisedPasswordDecision passwordDecision = compromisedPasswordChecker.check(password);

        if (passwordDecision.isCompromised()) {
            errors.put("password", "Wybierz silne hasło.");
        }

        return errors;
    }

    private Map<String, String> validateEmailAndMobileNumber(final String email, final String mobileNumber) {
        Map<String, String> errors = new HashMap<>();

        List<Customer> existingCustomers = customerRepository
                .findAllByEmailOrMobileNumber(email, mobileNumber);

        for (Customer customer : existingCustomers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                errors.put("email", "Podany adres e-mail jest już w użyciu.");
            }

            if (customer.getMobileNumber().equalsIgnoreCase(mobileNumber)) {
                errors.put("mobileNumber", "Podany numer telefonu jest już w użyciu.");
            }
        }

        return errors;
    }

}
