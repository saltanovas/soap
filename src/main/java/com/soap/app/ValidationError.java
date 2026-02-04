package com.soap.app;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValidationError {
    INVALID_CONTACT_NUMBER("400", "Invalid contact number format"),
    SERVICE_NOT_FOUND("404", "Service ID not found"),
    SERVICE_ALREADY_EXISTS("409", "Service with given ID already exists"),
    ;

    private final String code;
    private final String message;
}
