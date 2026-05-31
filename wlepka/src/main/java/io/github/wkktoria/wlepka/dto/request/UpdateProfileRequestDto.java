package io.github.wkktoria.wlepka.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequestDto(
        @NotBlank(message = "Imię jest wymagana.")
        @Size(min = 5, max = 30, message = "Długość imienia powinna zawierać od {min} do {max} znaków.")
        String name,

        @NotBlank(message = "Adres e-mail jest wymagany.")
        @Email(message = "Adres e-mail musi być prawidłowy.")
        String email,

        @NotBlank(message = "Numer telefonu jest wymagany.")
        @Pattern(regexp = "^\\d{9}$", message = "Numer telefonu musi składać się z 9 cyfr.")
        String mobileNumber,

        @NotBlank(message = "Ulica jest wymagana.")
        @Size(min = 5, max = 30, message = "Długość nazwy ulicy powinna zawierać od {min} do {max} znaków.")
        String street,

        @NotBlank(message = "Miasto jest wymagane.")
        @Size(min = 5, max = 30, message = "Długość nazwy miasta powinna zawierać od {min} do {max} znaków.")
        String city,

        @NotBlank(message = "Województwo jest wymagana.")
        @Size(min = 2, max = 20, message = "Długość nazwy województwa powinna zawierać od {min} do {max} znaków.")
        String state,

        @NotBlank(message = "Kod pocztowy jest wymagany.")
        @Pattern(regexp = "^\\d{2}-\\d{3}$", message = "Kod pocztowy musi być prawidłowy (xx-xxx).")
        String postalCode,

        @NotBlank(message = "Kraj jest wymagany.")
        @Size(min = 3, max = 30, message = "Długość nazwy kraju powinna zawierać od {min} do {max} znaków.")
        String country
) {
}
