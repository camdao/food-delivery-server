package com.delivery.domain.restaurant.dao;

import com.delivery.domain.restaurant.domain.Restaurant;

public interface RestaurantRepositoryCustom {
    Restaurant findRestaurantById(Long id);
}
