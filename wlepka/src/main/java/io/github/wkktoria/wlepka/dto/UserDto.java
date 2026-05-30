package io.github.wkktoria.wlepka.dto;

import lombok.Builder;

@Builder
public record UserDto(Long userId, String name, String email, String mobileNumber) {
}
