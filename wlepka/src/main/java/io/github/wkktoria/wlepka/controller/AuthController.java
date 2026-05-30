package io.github.wkktoria.wlepka.controller;

import io.github.wkktoria.wlepka.dto.request.LoginRequestDto;
import io.github.wkktoria.wlepka.dto.response.LoginResponseDto;
import io.github.wkktoria.wlepka.util.JwtUtil;
import lombok.RequiredArgsConstructor;
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
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> apiLogin(@RequestBody final LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDto.username(), loginRequestDto.password()
            ));
            String token = jwtUtil.generateJwt(authentication);

            return ResponseEntity.ok(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), null, token));
        } catch (BadCredentialsException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Nieprawidłowa nazwa użytkownika lub hasło.");
        } catch (AuthenticationException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Uwierzytelnianie się nie powiodło.");
        } catch (Exception ex) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Wystąpił nieoczekiwany błąd.");
        }
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(final HttpStatus status, final String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }

}
