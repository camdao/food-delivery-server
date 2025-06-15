package com.delivery.domain.restaurant.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RestaurantUpdateRequest(
        @NotNull(message = "name not null")
                @Schema(name = "name restaurant", description = "name restaurant")
                String name,
        @NotNull(message = "describe not null")
                @Schema(name = "describe restaurant", description = "describe restaurant")
                String describe) {}
