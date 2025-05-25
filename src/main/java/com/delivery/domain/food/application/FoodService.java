package com.delivery.domain.food.application;

import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodCreateResponse createFood(FoodCreateRequest foodCreaTeRequest) {
        // TO DO: handle image
        Food food = Food.createFood(foodCreaTeRequest.name(), foodCreaTeRequest.price());
        return FoodCreateResponse.of(foodRepository.save(food));
    }
}
