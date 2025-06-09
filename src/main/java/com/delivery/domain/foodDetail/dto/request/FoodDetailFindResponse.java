package com.delivery.domain.foodDetail.dto.request;

import com.delivery.domain.foodDetail.domain.FoodDetail;
import io.swagger.v3.oas.annotations.media.Schema;

public record FoodDetailFindResponse(
        @Schema(description = "food detail id", defaultValue = "default food detail id") Long id,
        @Schema(description = "food price", defaultValue = "default price") Long price,
        @Schema(description = "food size", defaultValue = "default size") String size) {
    public static FoodDetailFindResponse from(FoodDetail foodDetail) {
        return new FoodDetailFindResponse(
                foodDetail.getId(), foodDetail.getPrice(), foodDetail.getSize());
    }
}
