package org.example.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private final Long objectId;

    private final String objectType;

    public NotFoundException(Long objectId, String objectType) {
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public String getObjectType() {
        return objectType;
    }

}