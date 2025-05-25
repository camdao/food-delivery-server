package com.delivery.domain.food.dto.request;

import com.delivery.domain.food.domain.FoodStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record FoodCreateRequest(
        @Schema(description = "food name", defaultValue = "default name") String name,
        @Schema(description = "food price", defaultValue = "1") Long price,
        FoodStatus status) {}
