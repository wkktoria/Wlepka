package io.github.wkktoria.wlepka.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProfileResponseDto {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private boolean emailUpdated;
}
