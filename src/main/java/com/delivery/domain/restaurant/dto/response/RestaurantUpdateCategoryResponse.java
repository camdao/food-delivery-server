package com.delivery.domain.restaurant.dto.response;

import com.delivery.domain.restaurant.domain.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;

public record RestaurantUpdateCategoryResponse(
        @Schema(name = "id restaurant", description = "id restaurant") Long id,
        @Schema(name = "name restaurant", description = "name restaurant") String name,
        @Schema(name = "describe restaurant", description = "describe restaurant")
                String describe) {
    public static RestaurantUpdateCategoryResponse from(Restaurant restaurant) {
        return new RestaurantUpdateCategoryResponse(
                restaurant.getId(), restaurant.getName(), restaurant.getDescribe());
    }
}
