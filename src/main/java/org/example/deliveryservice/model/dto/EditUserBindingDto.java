package org.example.deliveryservice.model.dto;

import jakarta.validation.constraints.Size;

public class EditUserBindingDto {

    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    private String firstName;

    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    private String lastName;

    public EditUserBindingDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public EditUserBindingDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EditUserBindingDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

}
