package com.delivery.domain.food.dto.response;

import com.delivery.domain.food.domain.Food;
import com.delivery.domain.food.domain.FoodStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record FoodFindAllResponse(
        @Schema(description = "food id", defaultValue = "1") Long id,
        @Schema(description = "food name", defaultValue = "default name") String name,
        @Schema(description = "food price", defaultValue = "1") Long price,
        @Schema(description = "food status", defaultValue = "AVAILABLE") FoodStatus status) {
    public static FoodFindAllResponse from(Food food) {
        return new FoodFindAllResponse(
                food.getId(), food.getName(), food.getPrice(), food.getStatus());
    }
}
