package com.api.module.infoweb.services;

import com.api.module.infoweb.dtos.ContactDto;
import com.api.module.infoweb.models.Contact;
import com.api.module.infoweb.repositories.ContactRepository;
import com.api.module.infoweb.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactService implements CustomIActions<ContactDto> {
    private final ContactRepository contactRepository;

    @Override
    public CustomResponse create(ContactDto contactDto) {
        var obj = Contact.builder()
                .location(contactDto.getLocation())
                .email(contactDto.getEmail())
                .phone(contactDto.getPhone())
                .hide(contactDto.getHide())
                .build();

        var saved = contactRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse update(UUID id, ContactDto contactDto) {
        return null;
    }

    @Override
    public CustomResponse delete(UUID id) {
        return null;
    }
}
