package com.delivery.domain.restaurant.dto.response;

import com.delivery.domain.restaurant.domain.Restaurant;

public record RestaurantFindResponse(String name, String describe, String imgUrl) {

    public static RestaurantFindResponse from(Restaurant restaurant) {
        return new RestaurantFindResponse(
                restaurant.getName(), restaurant.getDescribe(), restaurant.getImgUrl());
    }
}
