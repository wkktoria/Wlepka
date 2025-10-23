package io.github.wkktoria.wlepka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDto {

    private Long id;
    private String name;
    private String email;
    private String mobileNumber;
    private String message;

}
