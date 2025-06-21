package com.delivery.domain.restaurant.application;

import com.delivery.domain.category.repository.CategoryRepository;
import com.delivery.domain.member.domain.Member;
import com.delivery.domain.restaurant.dao.RestaurantRepository;
import com.delivery.domain.restaurant.domain.Restaurant;
import com.delivery.domain.restaurant.dto.request.RestaurantCreateRequest;
import com.delivery.domain.restaurant.dto.request.RestaurantUpdateRequest;
import com.delivery.domain.restaurant.dto.response.RestaurantCreateResponse;
import com.delivery.domain.restaurant.dto.response.RestaurantFindResponse;
import com.delivery.domain.restaurant.dto.response.RestaurantUpdateResponse;
import com.delivery.global.config.erro.exception.CustomException;
import com.delivery.global.config.erro.exception.ErrorCode;
import com.delivery.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;
    private final MemberUtil memberUtil;

    public RestaurantCreateResponse createRestaurant(
            RestaurantCreateRequest restaurantCreateRequest) {
        Member currentMember = memberUtil.getCurrentMember();
        Restaurant restaurantCheck = restaurantRepository.findByOwnerId(currentMember.getId());
        if (restaurantCheck != null) {
            throw new CustomException(ErrorCode.MEMBER_EXITS_RESTAURANT);
        }

        Restaurant restaurant =
                Restaurant.createRestaurant(
                        restaurantCreateRequest.name(),
                        restaurantCreateRequest.describe(),
                        currentMember);
        return RestaurantCreateResponse.from(restaurantRepository.save(restaurant));
    }

    @Transactional(readOnly = true)
    public RestaurantFindResponse findRestaurant(Long restaurantId) {
        Member currentMember = memberUtil.getCurrentMember();
        Restaurant restaurant = restaurantRepository.findRestaurantById(restaurantId);

        validateRestaurantUserMismatch(restaurant, currentMember);
        return RestaurantFindResponse.from(restaurant);
    }

    public RestaurantUpdateResponse updateRestaurant(
            Long restaurantId, RestaurantUpdateRequest restaurantUpdateRequest) {

        Member currentMember = memberUtil.getCurrentMember();

        Restaurant restaurant =
                restaurantRepository
                        .findById(restaurantId)
                        .orElseThrow(() -> new CustomException(ErrorCode.RESTAURANT_NOT_FOUND));
        validateRestaurantUserMismatch(restaurant, currentMember);

        restaurant.update(restaurantUpdateRequest.name(), restaurantUpdateRequest.describe());

        return RestaurantUpdateResponse.from(restaurant);
    }

    private void validateRestaurantUserMismatch(Restaurant restaurant, Member member) {
        if (!restaurant.getId().equals(member.getRestaurant().getId())) {
            throw new CustomException(ErrorCode.RESTAURANT_MEMBER_MISMATCH);
        }
    }
}
