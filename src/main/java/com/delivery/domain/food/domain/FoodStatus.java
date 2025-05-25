package com.delivery.domain.food.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodStatus {
    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE"),
    DISCONTINUED("DISCONTINUED");

    private final String value;
}
