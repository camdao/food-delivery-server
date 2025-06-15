package com.delivery.domain.restaurant.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record RestaurantUpdateCategoryRequest(
        @NotNull(message = "categories not null")
                @Schema(name = "categories restaurant", description = "categories restaurant")
                List<Long> categories) {}
