package com.delivery.domain.foodDetail.application;

import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.domain.foodDetail.dao.FoodDetailRepository;
import com.delivery.domain.foodDetail.domain.FoodDetail;
import com.delivery.domain.foodDetail.dto.request.FoodDetailCreateRequest;
import com.delivery.domain.foodDetail.dto.request.FoodDetailUpdateRequest;
import com.delivery.domain.foodDetail.dto.response.FoodDetailCreateResponse;
import com.delivery.domain.foodDetail.dto.response.FoodDetailFindResponse;
import com.delivery.domain.foodDetail.dto.response.FoodDetailUpdateResponse;
import com.delivery.domain.member.domain.Member;
import com.delivery.domain.restaurant.dao.RestaurantRepository;
import com.delivery.domain.restaurant.domain.Restaurant;
import com.delivery.global.config.erro.exception.CustomException;
import com.delivery.global.config.erro.exception.ErrorCode;
import com.delivery.global.util.MemberUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodDetailService {
    private final FoodDetailRepository foodDetailRepository;
    private final FoodRepository foodRepository;
    private final MemberUtil memberUtil;
    private final RestaurantRepository restaurantRepository;

    public FoodDetailCreateResponse createFoodDetail(FoodDetailCreateRequest createRequest) {
        Food food =
                foodRepository
                        .findById(createRequest.foodId())
                        .orElseThrow(() -> new CustomException(ErrorCode.FOOD_NOT_FOUND));
        Member currentMember = memberUtil.getCurrentMember();
        Restaurant restaurant = restaurantRepository.findByOwnerId(currentMember.getId());

        validateFoodRestaurantMismatch(restaurant, food);

        FoodDetail foodDetailSave =
                foodDetailRepository.save(
                        FoodDetail.createFoodDetail(
                                createRequest.price(), createRequest.size(), food));
        return FoodDetailCreateResponse.of(food.getId(), foodDetailSave);
    }

    @Transactional(readOnly = true)
    public List<FoodDetailFindResponse> findFoodDetail(Long foodId) {
        Food food =
                foodRepository
                        .findById(foodId)
                        .orElseThrow(() -> new CustomException(ErrorCode.FOOD_NOT_FOUND));
        List<FoodDetail> foodDetails = foodDetailRepository.findAllByFood(foodId);
        return foodDetails.stream().map(FoodDetailFindResponse::from).toList();
    }

    public FoodDetailUpdateResponse updateFood(
            FoodDetailUpdateRequest updateRequest, Long foodDetailId) {
        FoodDetail foodDetail =
                foodDetailRepository
                        .findById(foodDetailId)
                        .orElseThrow(() -> new CustomException(ErrorCode.FOOD_NOT_FOUND));
        Member currentMember = memberUtil.getCurrentMember();
        Restaurant restaurant = restaurantRepository.findByOwnerId(currentMember.getId());

        // 2 query foodDetail.getFood()
        validateFoodRestaurantMismatch(restaurant, foodDetail.getFood());

        foodDetail.updateFoodDetail(
                updateRequest.price(), updateRequest.size(), updateRequest.status());
        return FoodDetailUpdateResponse.from(foodDetail);
    }

    private void validateFoodRestaurantMismatch(Restaurant restaurant, Food food) {
        if (!restaurant.getId().equals(food.getRestaurant().getId())) {
            throw new CustomException(ErrorCode.FOOD_DETAIL_RESTAURANT_MISMATCH);
        }
    }
}
