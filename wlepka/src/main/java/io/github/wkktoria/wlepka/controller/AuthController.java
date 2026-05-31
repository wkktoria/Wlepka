package io.github.wkktoria.wlepka.controller;

import io.github.wkktoria.wlepka.dto.UserDto;
import io.github.wkktoria.wlepka.dto.request.LoginRequestDto;
import io.github.wkktoria.wlepka.dto.request.RegisterRequestDto;
import io.github.wkktoria.wlepka.dto.response.LoginResponseDto;
import io.github.wkktoria.wlepka.dto.response.RegisterResponseDto;
import io.github.wkktoria.wlepka.entity.Customer;
import io.github.wkktoria.wlepka.repository.CustomerRepository;
import io.github.wkktoria.wlepka.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> apiLogin(@RequestBody final LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDto.username(), loginRequestDto.password()
            ));

            User loggedInUser = (User) authentication.getPrincipal();

            UserDto userDto = UserDto.builder()
                    .name(loggedInUser.getUsername())
                    .build();

            String token = jwtUtil.generateJwt(authentication);

            return ResponseEntity.ok(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), userDto, token));
        } catch (BadCredentialsException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Nieprawidłowa nazwa użytkownika lub hasło.");
        } catch (AuthenticationException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Uwierzytelnianie się nie powiodło.");
        } catch (Exception ex) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Wystąpił nieoczekiwany błąd.");
        }
    }

    @PostMapping("/register")
    ResponseEntity<RegisterResponseDto> apiRegister(@Valid @RequestBody final RegisterRequestDto registerRequestDto) {
        Optional<Customer> existingCustomer = customerRepository
                .findByEmailOrMobileNumber(registerRequestDto.email(), registerRequestDto.mobileNumber());

        if (existingCustomer.isPresent()) {
            Map<String, String> errors = new HashMap<>();
            Customer customer = existingCustomer.get();

            if (customer.getEmail().equalsIgnoreCase(registerRequestDto.email())) {
                errors.put("email", "Podany adres e-mail jest już w użyciu.");
            }

            if (customer.getMobileNumber().equalsIgnoreCase(registerRequestDto.mobileNumber())) {
                errors.put("mobileNumber", "Podany numer telefonu jest już w użyciu.");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RegisterResponseDto.builder()
                    .errors(errors)
                    .build());
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(registerRequestDto, customer);
        customer.setPasswordHash(passwordEncoder.encode(registerRequestDto.password()));
        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RegisterResponseDto.builder()
                        .message("Zarejestrowano pomyślnie!")
                        .build());
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(final HttpStatus status, final String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }

}
