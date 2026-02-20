package io.github.wkktoria.wlepka.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDto {

    @NotBlank(message = "Imię nie może być puste.")
    @Size(min = 3, max = 30, message = "Imię musi mieć długość między 3 a 30.")
    private String name;

    @NotBlank(message = "Adres e-mail nie może być pusty.")
    @Email(message = "Adres e-mail musi być prawidłowy.")
    private String email;

    @NotBlank(message = "Numer telefonu nie może być pusty.")
    @Pattern(regexp = "^\\d{9}$", message = "Numer telefonu musi składać się z 9 cyfr.")
    private String mobileNumber;

    @NotBlank(message = "Wiadomość nie może być pusta.")
    @Size(min = 5, max = 500, message = "Wiadomość musi mieć długość między 5 a 500.")
    private String message;

}
