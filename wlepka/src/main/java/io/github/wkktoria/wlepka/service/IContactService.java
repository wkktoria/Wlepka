package io.github.wkktoria.wlepka.service;

import io.github.wkktoria.wlepka.dto.ContactRequestDto;

public interface IContactService {

    boolean saveContact(ContactRequestDto contactRequestDto);

}
