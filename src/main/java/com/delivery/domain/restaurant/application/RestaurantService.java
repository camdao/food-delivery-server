package com.delivery.domain.restaurant.application;

import com.delivery.domain.restaurant.dao.RestaurantRepository;
import com.delivery.domain.restaurant.domain.Restaurant;
import com.delivery.domain.restaurant.dto.request.RestaurantCreateRequest;
import com.delivery.domain.restaurant.dto.request.RestaurantUpdateRequest;
import com.delivery.domain.restaurant.dto.response.RestaurantCreateResponse;
import com.delivery.domain.restaurant.dto.response.RestaurantFindResponse;
import com.delivery.domain.restaurant.dto.response.RestaurantUpdateResponse;
import com.delivery.global.config.erro.exception.CustomException;
import com.delivery.global.config.erro.exception.ErrorCode;
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

    @Transactional(readOnly = true)
    public RestaurantFindResponse findRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(restaurantId);
        return RestaurantFindResponse.from(restaurant);
    }

    public RestaurantUpdateResponse updateRestaurant(
            Long restaurantId, RestaurantUpdateRequest restaurantUpdateRequest) {

        Restaurant restaurant =
                restaurantRepository
                        .findById(restaurantId)
                        .orElseThrow(() -> new CustomException(ErrorCode.RESTAURANT_NOT_FOUND));

        restaurant.update(restaurantUpdateRequest.name(), restaurantUpdateRequest.describe());

        return RestaurantUpdateResponse.from(restaurant);
    }

    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
}
