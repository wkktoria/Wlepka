package io.github.wkktoria.wlepka.controller;

import io.github.wkktoria.wlepka.dto.request.LoginRequestDto;
import io.github.wkktoria.wlepka.dto.response.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthController {

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> apiLogin(@RequestBody final LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), null, null));
    }

}
