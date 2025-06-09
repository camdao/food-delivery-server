package com.delivery.domain.food.application;

import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.request.FoodUpdateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.delivery.domain.food.dto.response.FoodFindAllResponse;
import com.delivery.domain.food.dto.response.FoodUpdateResponse;
import com.delivery.domain.member.domain.Member;
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

    public FoodCreateResponse createFood(FoodCreateRequest foodCreaTeRequest) {
        // TO DO: handle image
        Member currentMember = memberUtil.getCurrentMember();
        Food food =
                Food.createFood(
                        foodCreaTeRequest.name(), foodCreaTeRequest.describe(), currentMember);
        return FoodCreateResponse.from(foodRepository.save(food));
    }

    @Transactional(readOnly = true)
    public List<FoodFindAllResponse> findAllFood() {
        Member currentMember = memberUtil.getCurrentMember();
        List<FoodFindAllResponse> results = new ArrayList<>();

        List<Food> foods = foodRepository.findByMemberId(currentMember.getId());

        for (Food food : foods) {
            results.add(FoodFindAllResponse.from(food));
        }
        return results;
    }

    public FoodUpdateResponse updateFood(FoodUpdateRequest updateRequest, Long foodId) {
        Food food =
                foodRepository
                        .findById(foodId)
                        .orElseThrow(() -> new CustomException(ErrorCode.Food_NOT_FOUND));

        food.updateFood(updateRequest.name(), updateRequest.status());
        return FoodUpdateResponse.from(food);
    }
}
