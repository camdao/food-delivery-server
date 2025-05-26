package com.delivery.domain.food.application;

import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.delivery.domain.food.dto.response.FoodFindAllResponse;
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

    public FoodCreateResponse createFood(FoodCreateRequest foodCreaTeRequest) {
        // TO DO: handle image, create by memberId
        Food food = Food.createFood(foodCreaTeRequest.name(), foodCreaTeRequest.price());
        return FoodCreateResponse.from(foodRepository.save(food));
    }

    @Transactional(readOnly = true)
    public List<FoodFindAllResponse> FoodFindAll() {
        // TO DO: memberId
        List<Food> foods = foodRepository.findAll();
        List<FoodFindAllResponse> results = new ArrayList<>();
        for (Food food : foods) {
            results.add(FoodFindAllResponse.from(food));
        }
        return results;
    }
}
