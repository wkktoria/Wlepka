package io.github.wkktoria.wlepka.dto.response;

import lombok.Builder;

@Builder
public record ProfileResponseDto(
        Long customerId,
        String name,
        String email,
        String mobileNumber,
        String street,
        String city,
        String state,
        String postalCode,
        String country,
        boolean emailUpdated
) {
}
