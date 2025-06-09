package com.delivery.domain.foodDetail.application;

import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.domain.foodDetail.domain.FoodDetail;
import com.delivery.domain.foodDetail.dto.request.FoodDetailCreateRequest;
import com.delivery.domain.foodDetail.dto.request.FoodDetailUpdateRequest;
import com.delivery.domain.foodDetail.dto.response.FoodDetailCreateResponse;
import com.delivery.domain.foodDetail.dto.response.FoodDetailFindResponse;
import com.delivery.domain.foodDetail.dto.response.FoodDetailUpdateResponse;
import com.delivery.domain.foodDetail.respository.FoodDetailRepository;
import com.delivery.domain.member.domain.Member;
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

    public FoodDetailCreateResponse createFoodDetail(FoodDetailCreateRequest createRequest) {
        Food food =
                foodRepository
                        .findById(createRequest.foodId())
                        .orElseThrow(() -> new CustomException(ErrorCode.Food_NOT_FOUND));
        Member member = memberUtil.getCurrentMember();

        validateFoodUserMismatch(food, member);

        FoodDetail foodDetailSave =
                foodDetailRepository.save(
                        FoodDetail.createFoodDetail(
                                createRequest.price(), createRequest.size(), food));
        return FoodDetailCreateResponse.of(food.getId(), foodDetailSave);
    }

    public List<FoodDetailFindResponse> findFoodDetail(Long foodId) {
        Food food =
                foodRepository
                        .findById(foodId)
                        .orElseThrow(() -> new CustomException(ErrorCode.Food_NOT_FOUND));
        List<FoodDetail> foodDetails = foodDetailRepository.findAllByFood(foodId);
        return foodDetails.stream().map(FoodDetailFindResponse::from).toList();
    }

    public FoodDetailUpdateResponse updateFood(
            FoodDetailUpdateRequest updateRequest, Long foodDetailId) {
        FoodDetail foodDetail =
                foodDetailRepository
                        .findById(foodDetailId)
                        .orElseThrow(() -> new CustomException(ErrorCode.Food_NOT_FOUND));
        Member member = memberUtil.getCurrentMember();

        validateFoodUserMismatch(foodDetail.getFood(), member);

        foodDetail.updateFoodDetail(
                updateRequest.price(), updateRequest.size(), updateRequest.status());
        return FoodDetailUpdateResponse.from(foodDetail);
    }

    private void validateFoodUserMismatch(Food food, Member member) {
        if (!food.getMember().getId().equals(member.getId())) {
            throw new CustomException(ErrorCode.FOOD_DETAIL_USER_MISMATCH);
        }
    }
}
