package org.example.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongCategoryException extends RuntimeException{

    private final String category;

    public String getCategory() {
        return category;
    }

    public WrongCategoryException(String category) {
        this.category = category;
    }

}
