package org.example.deliveryservice.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.deliveryservice.model.enums.GenderEnum;
import org.example.deliveryservice.validation.UniqueUsername;

public class UserRegistrationBindingDto {

    @NotNull
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    private String lastName;

    @NotNull
    @UniqueUsername
    private String username;

    @Email(message = "Email must be provided")
    @NotNull
    private String email;

    @NotNull
    @Size(min = 4, max = 20)
    private String password;

    @NotNull
    @Size(min = 4, max = 20)
    private String confirmPassword;

    @Positive
    @NotNull(message = "Age should be provided.")
    private Integer age;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum gender;

    public UserRegistrationBindingDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserRegistrationBindingDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegistrationBindingDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserRegistrationBindingDto setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}

