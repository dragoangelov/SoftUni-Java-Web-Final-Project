package org.example.deliveryservice.service;

import org.example.deliveryservice.model.dto.ContactBindingDto;
import org.example.deliveryservice.model.entity.ContactEntity;
import org.example.deliveryservice.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public ContactService(ModelMapper modelMapper,
                          ContactRepository contactRepository) {
        this.modelMapper = modelMapper;
        this.contactRepository = contactRepository;
    }

    public void saveContactMessage(ContactBindingDto contactBinding) {

        ContactEntity contactToSave = mapToContactEntity(contactBinding);

        contactToSave.setCreatedOn(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now())));

        this.contactRepository.saveAndFlush(contactToSave);

    }

    public ContactEntity mapToContactEntity(ContactBindingDto contactBinding) {
        return this.modelMapper.map(contactBinding, ContactEntity.class);
    }

}
