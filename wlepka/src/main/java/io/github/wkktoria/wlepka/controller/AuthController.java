package io.github.wkktoria.wlepka.controller;

import io.github.wkktoria.wlepka.dto.UserDto;
import io.github.wkktoria.wlepka.dto.request.LoginRequestDto;
import io.github.wkktoria.wlepka.dto.request.RegisterRequestDto;
import io.github.wkktoria.wlepka.dto.response.LoginResponseDto;
import io.github.wkktoria.wlepka.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthController {

    private final AuthenticationManager authenticationManager;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
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
    ResponseEntity<String> apiRegister(@Valid @RequestBody final RegisterRequestDto registerRequestDto) {
        inMemoryUserDetailsManager.createUser(new User(
                registerRequestDto.email(),
                passwordEncoder.encode(registerRequestDto.password()),
                List.of(new SimpleGrantedAuthority("USER"))
        ));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Zarejestrowano pomyślnie!");
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(final HttpStatus status, final String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }

}
