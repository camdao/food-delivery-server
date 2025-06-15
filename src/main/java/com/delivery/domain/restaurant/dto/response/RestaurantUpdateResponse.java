package com.delivery.domain.restaurant.dto.response;

import com.delivery.domain.restaurant.domain.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;

public record RestaurantUpdateResponse(
        @Schema(name = "id restaurant", description = "id restaurant") Long id,
        @Schema(name = "name restaurant", description = "name restaurant") String name,
        @Schema(name = "describe restaurant", description = "describe restaurant")
                String describe) {
    public static RestaurantUpdateResponse from(Restaurant restaurant) {
        return new RestaurantUpdateResponse(
                restaurant.getId(), restaurant.getName(), restaurant.getDescribe());
    }
}
