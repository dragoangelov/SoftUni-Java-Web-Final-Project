package org.example.deliveryservice.model.dto;


import org.example.deliveryservice.model.enums.ProductCategoryEnum;

import java.math.BigDecimal;

public class ProductViewDto {
    private Long id;
    private String name;
    private ProductCategoryEnum category;
    private BigDecimal price;
    private String description;

    public ProductViewDto() {
    }

    public Long getId() {
        return id;
    }

    public ProductViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductViewDto setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductViewDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductViewDto setDescription(String description) {
        this.description = description;
        return this;
    }

}
