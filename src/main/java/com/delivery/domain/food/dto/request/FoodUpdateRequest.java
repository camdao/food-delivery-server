package com.delivery.domain.food.dto.request;

import com.delivery.domain.food.domain.FoodStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FoodUpdateRequest(
        @NotNull(message = "Food name cannot be blank.")
                @Schema(description = "food name", defaultValue = "default name")
                String name,
        @NotNull(message = "Food price cannot be blank.")
                @Min(value = 0, message = "The minimum value for price is 0.")
                @Schema(description = "food price", defaultValue = "1")
                Long price,
        @NotNull(message = "Food status cannot be blank.")
                @Schema(description = "food status", defaultValue = "AVAILABLE")
                FoodStatus status) {}
