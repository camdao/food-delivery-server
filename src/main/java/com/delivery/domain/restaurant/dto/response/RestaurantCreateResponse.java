package com.delivery.domain.restaurant.dto.response;

import com.delivery.domain.restaurant.domain.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;

public record RestaurantCreateResponse(
        @Schema(name = "id restaurant", description = "id restaurant") Long id,
        @Schema(name = "name restaurant", description = "name restaurant") String name,
        @Schema(name = "describe restaurant", description = "describe restaurant")
                String describe) {

    public static RestaurantCreateResponse from(Restaurant restaurant) {
        return new RestaurantCreateResponse(
                restaurant.getId(), restaurant.getName(), restaurant.getDescribe());
    }
}
