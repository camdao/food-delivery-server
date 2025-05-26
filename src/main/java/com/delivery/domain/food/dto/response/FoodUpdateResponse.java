package com.delivery.domain.food.dto.response;

import com.delivery.domain.food.domain.Food;
import com.delivery.domain.food.domain.FoodStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record FoodUpdateResponse(
        @Schema(description = "food id", defaultValue = "1") Long id,
        @Schema(description = "food name", defaultValue = "default name") String name,
        @Schema(description = "food price", defaultValue = "1") Long price,
        @Schema(description = "food status", defaultValue = "AVAILABLE") FoodStatus status) {
    public static FoodUpdateResponse from(Food food) {
        return new FoodUpdateResponse(
                food.getId(), food.getName(), food.getPrice(), food.getStatus());
    }
}
