package io.github.wkktoria.wlepka.controller;

import io.github.wkktoria.wlepka.dto.ContactRequestDto;
import io.github.wkktoria.wlepka.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping
    public String saveContact(@RequestBody final ContactRequestDto contactRequestDto) {
        boolean isSaved = contactService.saveContact(contactRequestDto);

        if (isSaved) {
            return "Wiadomość została wysłana.";
        } else {
            return "Wystąpił błąd. Spróbuj ponownie.";
        }
    }

}
