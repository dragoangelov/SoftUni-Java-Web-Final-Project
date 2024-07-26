package org.example.deliveryservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class ContactBindingDto {

    @Size(min = 3, max = 200)
    private String name;

    @Email
    private String email;

    @Size(min = 3, message = "Subject must be at least 3 symbols")
    private String subject;

    @Size(min = 5, max = 2000, message = "Description must be between 5 and 2000 symbols.")
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
