package io.github.wkktoria.wlepka.service.impl;

import io.github.wkktoria.wlepka.dto.ContactRequestDto;
import io.github.wkktoria.wlepka.entity.Contact;
import io.github.wkktoria.wlepka.repository.ContactRepository;
import io.github.wkktoria.wlepka.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    public boolean saveContact(final ContactRequestDto contactRequestDto) {
        Contact contact = transformToEntity(contactRequestDto);
        contactRepository.save(contact);
        return true;
    }

    private Contact transformToEntity(final ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
        return contact;
    }

}
