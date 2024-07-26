package org.example.deliveryservice.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.deliveryservice.model.enums.ProductCategoryEnum;

import java.math.BigDecimal;

public class AddProductBindingDto {

    @Size(min = 3, max = 200)
    private String name;

    @Positive(message = "Price must be a positive number")
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductCategoryEnum category;

    @NotEmpty(message = "Description is required")
    private String description;

    public AddProductBindingDto() {
    }

    public String getName() {
        return name;
    }

    public AddProductBindingDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddProductBindingDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public AddProductBindingDto setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddProductBindingDto setDescription(String description) {
        this.description = description;
        return this;
    }

}
