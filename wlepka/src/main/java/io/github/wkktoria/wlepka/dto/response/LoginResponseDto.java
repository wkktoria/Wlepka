package io.github.wkktoria.wlepka.dto.response;

import io.github.wkktoria.wlepka.dto.UserDto;

public record LoginResponseDto(String message, UserDto userDto, String token) {
}
