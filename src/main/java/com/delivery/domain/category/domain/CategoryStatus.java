package com.delivery.domain.category.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CategoryStatus {
    ACTIVE("ACTIVE"),
    DELETE("DELETE");

    private final String value;
}
