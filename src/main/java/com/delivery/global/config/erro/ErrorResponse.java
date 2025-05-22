package com.delivery.global.config.erro;

public record ErrorResponse(String errorClassName, String message) {

    public static ErrorResponse of(String errorClassName, String message) {
        return new ErrorResponse(errorClassName, message);
    }
}
