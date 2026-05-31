package io.github.wkktoria.wlepka.controller;

import io.github.wkktoria.wlepka.dto.UserDto;
import io.github.wkktoria.wlepka.dto.request.LoginRequestDto;
import io.github.wkktoria.wlepka.dto.request.RegisterRequestDto;
import io.github.wkktoria.wlepka.dto.response.LoginResponseDto;
import io.github.wkktoria.wlepka.dto.response.RegisterResponseDto;
import io.github.wkktoria.wlepka.entity.Customer;
import io.github.wkktoria.wlepka.service.ICustomerService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthController {

    private final AuthenticationManager authenticationManager;
    private final ICustomerService customerService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> apiLogin(@RequestBody final LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDto.username(), loginRequestDto.password()
            ));

            Customer loggedInUser = (Customer) authentication.getPrincipal();
            UserDto userDto = UserDto.builder().build();
            BeanUtils.copyProperties(loggedInUser, userDto);

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
        RegisterResponseDto registerResponseDto = customerService.registerUser(registerRequestDto);

        if (registerResponseDto.errors() != null && !registerResponseDto.errors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(registerResponseDto);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registerResponseDto);
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(final HttpStatus status, final String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }

}
