package com.delivery.domain.foodDetail.service;

import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.domain.foodDetail.domain.FoodDetail;
import com.delivery.domain.foodDetail.dto.request.FoodDetailCreateResponse;
import com.delivery.domain.foodDetail.dto.response.FoodDetailCreateRequest;
import com.delivery.domain.foodDetail.respository.FoodDetailRepository;
import com.delivery.global.config.erro.exception.CustomException;
import com.delivery.global.config.erro.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodDetailService {
    private final FoodDetailRepository foodDetailRepository;
    private final FoodRepository foodRepository;

    public FoodDetailCreateResponse createFood(FoodDetailCreateRequest createRequest) {
        Food food =
                foodRepository
                        .findById(createRequest.foodId())
                        .orElseThrow(() -> new CustomException(ErrorCode.Food_NOT_FOUND));

        FoodDetail foodDetail =
                FoodDetail.createFoodDetail(createRequest.price(), createRequest.size(), food);
        FoodDetail foodDetailSave = foodDetailRepository.save(foodDetail);
        return FoodDetailCreateResponse.of(food.getId(), foodDetailSave);
    }
}
