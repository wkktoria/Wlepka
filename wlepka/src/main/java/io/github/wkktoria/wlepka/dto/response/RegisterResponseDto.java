package io.github.wkktoria.wlepka.dto.response;

import lombok.Builder;

import java.util.Map;

@Builder
public record RegisterResponseDto(String message, Map<String, String> errors) {
}
