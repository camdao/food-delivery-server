package com.delivery.domain.foodDetail.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodDetailStatus {
    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE"),
    DISCONTINUED("DISCONTINUED");

    private final String value;
}
