package com.delivery.global.config.erro.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SAMPLE_ERROR(HttpStatus.BAD_REQUEST, "Sample Error Message"),

    // Common
    METHOD_ARGUMENT_TYPE_MISMATCH(
            HttpStatus.BAD_REQUEST, "Binding failed because the enum types do not match."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "The HTTP method is not supported."),
    INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR, "Server error, please contact your administrator"),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "The member you are looking for could not be found."),

    // food
    Food_NOT_FOUND(HttpStatus.NOT_FOUND, "The food could not be found.");
    private final HttpStatus status;
    private final String message;
}
