package org.example.deliveryservice.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

public class OrderBindingDto {

    private String comment;

    private String address;

    @Size(min = 10, max = 10)
    private String contactNumber;


    public OrderBindingDto() {
    }


    public String getComment() {
        return comment;
    }

    public OrderBindingDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderBindingDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public OrderBindingDto setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }
}
