package com.delivery.domain.food.dto.response;

import com.delivery.domain.food.domain.Food;
import com.delivery.domain.food.domain.FoodStatus;

public record FoodCreateResponse(Long id, String name, Long price, FoodStatus status) {

    public static FoodCreateResponse of(Food food) {
        return new FoodCreateResponse(
                food.getId(), food.getName(), food.getPrice(), food.getStatus());
    }
}
