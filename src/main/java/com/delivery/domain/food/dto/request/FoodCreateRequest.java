package com.delivery.domain.food.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record FoodCreateRequest(
        @NotNull(message = "Food name cannot be blank.")
                @Schema(description = "food name", defaultValue = "default name")
                String name,
        @NotNull(message = "Food describe cannot be blank.")
                @Schema(description = "food describe", defaultValue = "default describe")
                String describe) {}
