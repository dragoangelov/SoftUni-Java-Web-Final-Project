package org.example.deliveryservice.model.dto;

import jakarta.validation.constraints.Size;

public class ContactBindingDto {

    private String name;

    private String email;
    @Size(min = 3)
    private String subject;
    @Size(min = 5, max = 2000)
    private String description;

    public ContactBindingDto() {
    }

    public String getName() {
        return name;
    }

    public ContactBindingDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactBindingDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ContactBindingDto setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ContactBindingDto setDescription(String description) {
        this.description = description;
        return this;
    }

}
