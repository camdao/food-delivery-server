package com.delivery.domain.food.application;

import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.delivery.domain.food.dto.response.FoodFindAllResponse;
import com.delivery.domain.member.domain.Member;
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
                Food.createFood(foodCreaTeRequest.name(), foodCreaTeRequest.price(), currentMember);
        return FoodCreateResponse.from(foodRepository.save(food));
    }

    @Transactional(readOnly = true)
    public List<FoodFindAllResponse> FoodFindAll() {
        Member currentMember = memberUtil.getCurrentMember();
        List<FoodFindAllResponse> results = new ArrayList<>();

        List<Food> foods = foodRepository.findByMemberId(currentMember.getId());

        for (Food food : foods) {
            results.add(FoodFindAllResponse.from(food));
        }
        return results;
    }
}
