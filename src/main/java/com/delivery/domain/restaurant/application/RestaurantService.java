package com.delivery.domain.restaurant.application;

import com.delivery.domain.restaurant.dao.RestaurantRepository;
import com.delivery.domain.restaurant.domain.Restaurant;
import com.delivery.domain.restaurant.dto.request.RestaurantCreateRequest;
import com.delivery.domain.restaurant.dto.response.RestaurantCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantCreateResponse createRestaurant(
            RestaurantCreateRequest restaurantCreateRequest) {
        Restaurant restaurant =
                Restaurant.createRestaurant(
                        restaurantCreateRequest.name(), restaurantCreateRequest.describe());
        return RestaurantCreateResponse.from(restaurantRepository.save(restaurant));
    }
}
