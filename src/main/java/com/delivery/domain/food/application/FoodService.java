package com.delivery.domain.food.application;

import com.delivery.domain.category.domain.Category;
import com.delivery.domain.category.repository.CategoryRepository;
import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.request.FoodUpdateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.delivery.domain.food.dto.response.FoodFindAllResponse;
import com.delivery.domain.food.dto.response.FoodUpdateResponse;
import com.delivery.domain.member.domain.Member;
import com.delivery.domain.restaurant.dao.RestaurantRepository;
import com.delivery.domain.restaurant.domain.Restaurant;
import com.delivery.global.config.erro.exception.CustomException;
import com.delivery.global.config.erro.exception.ErrorCode;
import com.delivery.global.util.MemberUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final MemberUtil memberUtil;
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    public FoodCreateResponse createFood(FoodCreateRequest foodCreateRequest) {
        // TO DO: handle image
        Member currentMember = memberUtil.getCurrentMember();
        Restaurant restaurant = restaurantRepository.findByOwnerId(currentMember.getId());

        if (restaurant == null) {
            throw new CustomException(ErrorCode.RESTAURANT_NOT_FOUND);
        }

        Category category =
                categoryRepository
                        .findById(foodCreateRequest.categoryId())
                        .orElseThrow(() -> new CustomException(ErrorCode.FOOD_NOT_FOUND));

        Food food =
                Food.createFood(
                        foodCreateRequest.name(),
                        foodCreateRequest.describe(),
                        category,
                        restaurant);

        return FoodCreateResponse.from(foodRepository.save(food));
    }

    @Transactional(readOnly = true)
    public List<FoodFindAllResponse> findAllFood() {
        Member currentMember = memberUtil.getCurrentMember();
        Restaurant restaurant = restaurantRepository.findByOwnerId(currentMember.getId());

        List<FoodFindAllResponse> results = new ArrayList<>();

        List<Food> foods = foodRepository.findByOwnerId(restaurant.getId());

        for (Food food : foods) {
            results.add(FoodFindAllResponse.from(food));
        }
        return results;
    }

    public FoodUpdateResponse updateFood(FoodUpdateRequest updateRequest, Long foodId) {
        Member currentMember = memberUtil.getCurrentMember();
        Restaurant restaurant = restaurantRepository.findByOwnerId(currentMember.getId());

        Food food =
                foodRepository
                        .findById(foodId)
                        .orElseThrow(() -> new CustomException(ErrorCode.FOOD_NOT_FOUND));

        validateFoodRestaurantMismatch(restaurant, food);

        food.updateFood(updateRequest.name(), updateRequest.status());
        return FoodUpdateResponse.from(food);
    }

    private void validateFoodRestaurantMismatch(Restaurant restaurant, Food food) {
        if (!restaurant.getId().equals(food.getRestaurant().getId())) {
            throw new CustomException(ErrorCode.FOOD_DETAIL_RESTAURANT_MISMATCH);
        }
    }
}
