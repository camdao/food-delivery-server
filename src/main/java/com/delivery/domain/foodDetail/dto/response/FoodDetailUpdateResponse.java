package com.delivery.domain.foodDetail.dto.response;

import com.delivery.domain.foodDetail.domain.FoodDetail;
import com.delivery.domain.foodDetail.domain.FoodDetailStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record FoodDetailUpdateResponse(
        @Schema(description = "food price", defaultValue = "default price") Long price,
        @Schema(description = "food size", defaultValue = "default size") String size,
        @Schema(description = "food status", defaultValue = "AVAILABLE") FoodDetailStatus status) {

    public static FoodDetailUpdateResponse from(FoodDetail foodDetail) {
        return new FoodDetailUpdateResponse(
                foodDetail.getPrice(), foodDetail.getSize(), foodDetail.getStatus());
    }
}
