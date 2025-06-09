package com.delivery.domain.foodDetail.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record FoodDetailCreateRequest(
        @NotNull(message = "Food price cannot be blank.")
                @Schema(description = "food price", defaultValue = "default price")
                Long price,
        @NotNull(message = "Food size cannot be blank.")
                @Schema(description = "food size", defaultValue = "default size")
                String size,
        @NotNull(message = "Food id cannot be blank.")
                @Schema(description = "food id", defaultValue = "default food id")
                Long foodId) {}
