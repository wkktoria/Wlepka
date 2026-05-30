package io.github.wkktoria.wlepka.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @NotBlank(message = "Imię jest wymagana.")
        @Size(min = 5, max = 30, message = "Długość imienia powinna zawierać od {min} do {max} znaków.")
        String name,

        @NotBlank(message = "Adres e-mail jest wymagany.")
        @Email(message = "Adres e-mail musi być prawidłowy.")
        String email,

        @NotBlank(message = "Numer telefonu jest wymagany.")
        @Pattern(regexp = "^\\d{9}$", message = "Numer telefonu musi składać się z 9 cyfr.")
        String mobileNumber,

        @NotBlank(message = "Hasło jest wymagane.")
        @Size(min = 8, max = 255, message = "Długość hasła powinna zawierać od {min} do {max} znaków.")
        String password
) {
}
