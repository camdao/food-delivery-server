package com.delivery.domain.food.dao;

import com.delivery.domain.food.domain.Food;
import java.util.List;

public interface FoodRepositoryCustom {
    List<Food> findByOwnerId(Long restaurantId);
}
